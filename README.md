flowchart TD
    %% Định nghĩa các lớp màu
    classDef startEnd fill:#ffebee,stroke:#c62828,stroke-width:2px,color:#000;
    classDef inputData fill:#e1f5fe,stroke:#0277bd,stroke-width:2px,stroke-dasharray: 5 5,color:#000;
    classDef coreProcess fill:#e8eaf6,stroke:#283593,stroke-width:2px,color:#000;
    classDef criticalGap fill:#fff3e0,stroke:#e65100,stroke-width:2px,color:#000;
    classDef decision fill:#fffde7,stroke:#f57f17,stroke-width:2px,color:#000;
    classDef branch fill:#f1f8e9,stroke:#33691e,stroke-width:1.5px,color:#000;
    classDef outputGoal fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px,color:#000;

    %% Các nút và Hình khối tương ứng
    N1(["<b>1. KHỞI ĐIỂM: THÁCH THỨC ĐỒ ÁN NHÓM SE</b><br/>• Bất đồng nhóm & 'Ăn bám' (Free-riding)<br/>• PE được dùng phổ biến nhưng lộn xộn, thiếu chuẩn hóa"]):::startEnd
    
    N2[/"<b>2. DỮ LIỆU ĐẦU VÀO THỰC CHỨNG</b><br/>• 50 bài báo từ tổng quan tài liệu (SLR: 2007 - 2025)<br/>• Phỏng vấn sâu 7 chuyên gia/giảng viên SE quốc tế"/]:::inputData
    
    N3["<b>3. BỘ KHUNG TƯ DUY TAXONOMY 5 CHIỀU</b><br/>① Course Context (Bối cảnh)  ② Purpose (Mục đích)<br/>③ Participants (Đối tượng)   ④ Mechanism (Cơ chế)<br/>⑤ Use of Output (Sử dụng kết quả)"]:::coreProcess
    
    N4{{"<b>4. NHẬN DIỆN MÂU THUẪN & LỖ HỔNG THỰC TẾ</b><br/>• Chủ yếu dùng để trừ điểm (Summative), bỏ quên học tập (Formative)<br/>• Rất ít khi chia sẻ phản hồi ngược lại cho sinh viên<br/>• Nguy cơ cảm tính/bè phái vì THIẾU đối soát chéo (Triangulation)"}}:::criticalGap
    
    N5{"<b>5. MỤC TIÊU SƯ PHẠM ƯU TIÊN?</b>"}:::decision
    
    N6A["<b>HƯỚNG ĐI: VÌ HỌC TẬP (Formative)</b><br/>• Đánh giá định kỳ theo từng sprint<br/>• Chia sẻ nhận xét ẨN DANH để sửa đổi<br/>• Không chấm điểm gắt gao để khuyến khích trung thực"]:::branch
    
    N6B["<b>HƯỚNG ĐI: ĐỂ XẾP LOẠI (Summative)</b><br/>• Sử dụng bảng tiêu chí (Rubrics) chuẩn hóa<br/>• BẮT BUỘC đối soát chéo với commit Git & nhật ký<br/>• Giảng viên là người chốt điểm cuối cùng"]:::branch
    
    N7(["<b>6. ĐÍCH ĐẾN: HƯỚNG DẪN THIẾT KẾ ĐÁNH GIÁ (BẢNG 13)</b><br/>• Cân bằng sự đánh đổi (Trade-offs: Khối lượng công việc vs Độ sâu)<br/>• Tối ưu theo quy mô nhóm (Tránh survey fatigue)<br/>• Đảm bảo đánh giá công bằng, minh bạch và phát triển kỹ năng mềm"]):::outputGoal

    %% Liên kết luồng
    N1 --> N2
    N2 --> N3
    N3 --> N4
    N4 --> N5
    N5 -- "Học tập & Tự phản ánh" --> N6A
    N5 -- "Quy trách nhiệm & Cho điểm" --> N6B
    N6A --> N7
    N6B --> N7
