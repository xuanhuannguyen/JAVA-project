import os
import mysql.connector
from mysql.connector import Error
from sendgrid import SendGridAPIClient
from sendgrid.helpers.mail import Mail
from dotenv import load_dotenv
from datetime import date

load_dotenv()

def get_due_soon_books():
    print("METHOD 1: Lấy danh sách sách sắp hết hạn...")
    due_soon_list = []
    conn = None 
    try:
        conn = mysql.connector.connect(
            host=os.getenv('DB_HOST'),
            user=os.getenv('DB_USER'),
            password=os.getenv('DB_PASSWORD'),
            database=os.getenv('DB_NAME')
        )

        if conn.is_connected():
            print("=> Kết nối CSDL thành công.")
            cursor = conn.cursor(dictionary=True)
            query = """
                SELECT 
                    sd.student_id AS student_id,
                    sd.student_name AS student_name, 
                    sd.email AS student_email,
                    ib.book_name AS book_name,
                    ib.due_date AS due_date,
                    ib.id AS issue_id
                FROM 
                    issue_book_details AS ib
                JOIN 
                    student_details AS sd ON ib.student_id = sd.student_id
                WHERE 
                    ib.due_date = CURDATE() + INTERVAL 1 DAY 
                    AND ib.status = 'pending'
            """
            
            cursor.execute(query)
            due_soon_list = cursor.fetchall() 
            print(f"=> Tìm thấy {len(due_soon_list)} sách sẽ hết hạn vào ngày mai.")
            cursor.close()

    except Error as e:
        print(f"LỖI: Không thể kết nối hoặc truy vấn MySQL. Chi tiết: {e}")
        return None
    finally:
        # Luôn đảm bảo kết nối được đóng lại
        if conn and conn.is_connected():
            conn.close()
            print("=> Đã đóng kết nối MySQL.")
            
    return due_soon_list

def send_reminder_email(to_email, student_id, student_name, issue_id, book_name, due_date):
    subject = f"Thư viện FPT thông báo lịch trả sách sắp đến hạn"
    html_content = f"""
    <h3>Xin chào {student_name} với MSSV: {student_id},</h3>
    <p>Hệ thống Thư viện xin được nhắc bạn về lịch trả sách sắp đến hạn vào ngày mai.</p>
    <ul>
        <li><strong>ID mượn sách:</strong> {issue_id}</li>
        <li><strong>Tên sách:</strong> {book_name}</li>
        <li><strong>Ngày hết hạn:</strong> {due_date.strftime('%d-%m-%Y')}</li>
    </ul>
    <p>Vui lòng mang sách đến trả tại thư viện đúng hạn để không phát sinh phí trễ hạn.</p>
    <p>Cảm ơn bạn đã sử dụng dịch vụ của thư viện.</p>
    <p>Trân trọng,<br>Ban quản lý Thư viện</p>
    """
    
    message = Mail(
        from_email=os.getenv('FROM_EMAIL'),
        to_emails=to_email,
        subject=subject,
        html_content=html_content
    )
    
    try:
        sg = SendGridAPIClient(os.getenv('SENDGRID_API_KEY'))
        response = sg.send(message)
        print(f"Đã gửi email nhắc nhở thành công tới {to_email}, Status Code: {response.status_code}")
        return True
    except Exception as e:
        print(f"Lỗi khi gửi email tới {to_email}: {e}")
        return False
def get_overdue_books():
    """
    Hàm này lấy danh sách sách đã quá hạn từ cơ sở dữ liệu.
    """
    overdue_list = []
    conn = None 
    try:
        conn = mysql.connector.connect(
            host=os.getenv('DB_HOST'),
            user=os.getenv('DB_USER'),
            password=os.getenv('DB_PASSWORD'),
            database=os.getenv('DB_NAME')
        )

        if conn.is_connected():
            cursor = conn.cursor(dictionary=True)
            query = """
                SELECT 
                    sd.student_id AS student_id,
                    sd.student_name AS student_name, 
                    sd.email AS student_email,
                    ib.book_name AS book_name,
                    ib.due_date AS due_date,
                    ib.id AS issue_id
                FROM 
                    issue_book_details AS ib
                JOIN 
                    student_details AS sd ON ib.student_id = sd.student_id
                WHERE 
                    ib.due_date < CURDATE() 
                    AND ib.status = 'pending'
            """
            
            cursor.execute(query)
            overdue_list = cursor.fetchall() 
            cursor.close()

    except Error as e:
        print(f"LỖI: Không thể kết nối hoặc truy vấn MySQL. Chi tiết: {e}")
        return None
    finally:
        if conn and conn.is_connected():
            conn.close()
            
    return overdue_list
def calculate_fine(due_date):
    """
    Hàm này tính toán phí trễ hạn dựa trên ngày hết hạn.
    """
    if due_date < date.today():
        days_overdue = (date.today() - due_date).days
        fine = days_overdue * 15000 
        return fine
    return 0
def send_overdue_email(to_email, student_id, student_name, issue_id, book_name, due_date):
    """
    Hàm này gửi email thông báo sách đã quá hạn.
    """
    subject = f"Thông báo sách quá hạn - Thư viện FPT"
    fine = calculate_fine(due_date)
    html_content = f"""
    <h3>Xin chào {student_name} với MSSV: {student_id},</h3>
    <p>Chúng tôi xin thông báo rằng sách bạn mượn đã quá hạn.</p>
    <ul>
        <li><strong>ID mượn sách:</strong> {issue_id}</li>
        <li><strong>Tên sách:</strong> {book_name}</li>
        <li><strong>Ngày hết hạn:</strong> {due_date.strftime('%d-%m-%Y')}</li>
        <li><strong>Phí trễ hạn:</strong> {fine} VNĐ</li>
    </ul>
    <p>Vui lòng đến thư viện để thanh toán phí trễ hạn và trả sách.</p>
    <p>Cảm ơn bạn đã sử dụng dịch vụ của thư viện.</p>
    <p>Trân trọng,<br>Ban quản lý Thư viện</p>
    """
    
    message = Mail(
        from_email=os.getenv('FROM_EMAIL'),
        to_emails=to_email,
        subject=subject,
        html_content=html_content
    )
    
    try:
        sg = SendGridAPIClient(os.getenv('SENDGRID_API_KEY'))
        response = sg.send(message)
        print(f"Đã gửi email quá hạn thành công tới {to_email}, Status Code: {response.status_code}")
        return True
    except Exception as e:
        print(f"Lỗi khi gửi email quá hạn tới {to_email}: {e}")
        return False

def main():
    """
    Hàm chính điều phối toàn bộ quá trình: lấy dữ liệu -> gửi mail -> báo cáo.
    """
    print("="*40)
    print(f"Bắt đầu agent gửi mail nhắc nhở lúc: {date.today().strftime('%d-%m-%Y')}")
    print("="*40)
    
    # 1. Lấy danh sách sinh viên cần nhắc nhở
    due_soon_records = get_due_soon_books()
    
    if due_soon_records is None:
        print("Dừng chương trình do lỗi CSDL.")
        return

    if not due_soon_records:
        print("Không có sách nào sắp hết hạn vào ngày mai. Tác vụ hoàn thành.")
        print("="*40)
        return
        
    # 2. Gửi email cho từng người trong danh sách
    print(f"\nBắt đầu gửi email cho {len(due_soon_records)} sinh viên...")
    success_count = 0
    fail_count = 0
    
    for record in due_soon_records:
        if send_reminder_email(
            to_email=record['student_email'],
            student_id=record['student_id'],
            student_name=record['student_name'],
            issue_id=record['issue_id'],
            book_name=record['book_name'],
            due_date=record['due_date']
        ):
            success_count += 1
        else:
            fail_count += 1
            
    print("\n--- BÁO CÁO REMINDER HOÀN THÀNH ---")
    print(f"Gửi thành công: {success_count} email.")
    print(f"Gửi thất bại: {fail_count} email.")
    print("Agent đã chạy xong.")
    print("="*40)
    
    overdue_records = get_overdue_books() 
    if  overdue_records is None:
        print("Dừng chương trình do lỗi CSDL.")
        return
    if not overdue_records:
        print("Không có sách nào đã quá hạn. Tác vụ hoàn thành.")
        print("="*40)
        return
    print(f"\nBắt đầu gửi email quá hạn cho {len(overdue_records)} sinh viên...")
    success_count  = 0
    fail_count = 0
    for record in overdue_records:
        if send_overdue_email(
            to_email=record['student_email'],
            student_id=record['student_id'],
            student_name=record['student_name'],
            issue_id=record['issue_id'],
            book_name=record['book_name'],
            due_date=record['due_date']
        ):
            success_count += 1
        else:
            fail_count += 1
         
    # 3. In báo cáo tổng kết
    print("\n--- BÁO CÁO OVERDUE HOÀN THÀNH ---")
    print(f"Gửi thành công: {success_count} email.")
    print(f"Gửi thất bại: {fail_count} email.")
    print("Agent đã chạy xong.")
    print("="*40)

# Dòng này để đảm bảo hàm main() được gọi khi bạn chạy file python
if __name__ == '__main__':
    main()