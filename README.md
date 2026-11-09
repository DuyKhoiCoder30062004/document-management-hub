
**However, I recommend a few improvements before putting it into your final Markdown document:**

1. Remove HTML tags such as `<b>` and `<br/>` where possible; use Mermaid's native line-break syntax.
2. Shorten node descriptions to prevent excessively large flowchart boxes.
3. Preserve Vietnamese terminology but keep English academic terms in parentheses.
4. Improve node numbering so that the sequence remains consistent.
5. Keep the two decision branches visually distinguishable.

### Revised Mermaid code

<CodeBlock language="mermaid" editable>
flowchart TD
    %% Color definitions
    classDef startEnd fill:#ffebee,stroke:#c62828,stroke-width:2px,color:#000;
    classDef inputData fill:#e1f5fe,stroke:#0277bd,stroke-width:2px,stroke-dasharray:5 5,color:#000;
    classDef coreProcess fill:#e8eaf6,stroke:#283593,stroke-width:2px,color:#000;
    classDef criticalGap fill:#fff3e0,stroke:#e65100,stroke-width:2px,color:#000;
    classDef decision fill:#fffde7,stroke:#f57f17,stroke-width:2px,color:#000;
    classDef branch fill:#f1f8e9,stroke:#33691e,stroke-width:1.5px,color:#000;
    classDef outputGoal fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px,color:#000;

    %% 1. Starting point
    N1(["1. KHỞI ĐIỂM: THÁCH THỨC ĐỒ ÁN NHÓM SE<br/>• Bất đồng nhóm và hiện tượng ăn bám (Free-riding)<br/>• Đánh giá PE phổ biến nhưng thiếu chuẩn hóa"]):::startEnd

    %% 2. Empirical input
    N2[/"2. DỮ LIỆU ĐẦU VÀO THỰC CHỨNG<br/>• 50 bài báo từ tổng quan tài liệu (SLR, 2007–2025)<br/>• Phỏng vấn sâu 7 chuyên gia/giảng viên SE quốc tế"/]:::inputData

    %% 3. Analytical framework
    N3["3. KHUNG PHÂN LOẠI TAXONOMY 5 CHIỀU<br/>① Course Context – Bối cảnh môn học<br/>② Purpose – Mục đích<br/>③ Participants – Đối tượng tham gia<br/>④ Mechanism – Cơ chế<br/>⑤ Use of Output – Sử dụng kết quả"]:::coreProcess

    %% 4. Identifying gaps
    N4{{"4. NHẬN DIỆN MÂU THUẪN VÀ KHOẢNG TRỐNG<br/>• Thiên về đánh giá tổng kết (Summative)<br/>• Bỏ quên đánh giá hỗ trợ học tập (Formative)<br/>• Ít chia sẻ phản hồi cho sinh viên<br/>• Thiếu đối soát chéo (Triangulation), dễ dẫn đến cảm tính"}}:::criticalGap

    %% 5. Pedagogical decision
    N5{"5. MỤC TIÊU SƯ PHẠM ƯU TIÊN?"}:::decision

    %% 6A. Formative approach
    N6A["HƯỚNG A: HỖ TRỢ HỌC TẬP (FORMATIVE)<br/>• Đánh giá định kỳ theo từng Sprint<br/>• Chia sẻ nhận xét ẩn danh để cải thiện<br/>• Khuyến khích phản hồi trung thực"]:::branch

    %% 6B. Summative approach
    N6B["HƯỚNG B: XẾP LOẠI VÀ CHO ĐIỂM (SUMMATIVE)<br/>• Sử dụng Rubrics chuẩn hóa<br/>• Đối soát với Git commits và nhật ký<br/>• Giảng viên quyết định điểm cuối cùng"]:::branch

    %% 7. Final outcome
    N7(["6. ĐÍCH ĐẾN: HƯỚNG DẪN THIẾT KẾ ĐÁNH GIÁ (BẢNG 13)<br/>• Cân bằng khối lượng công việc và độ sâu đánh giá<br/>• Tối ưu theo quy mô nhóm, tránh Survey Fatigue<br/>• Đảm bảo công bằng, minh bạch và phát triển kỹ năng mềm"]):::outputGoal

    %% Connections
    N1 --> N2
    N2 --> N3
    N3 --> N4
    N4 --> N5

    N5 -->|"Học tập và tự phản ánh"| N6A
    N5 -->|"Quy trách nhiệm và cho điểm"| N6B

    N6A --> N7
    N6B --> N7
</CodeBlock>

### How to use it in your `.md` file



```mermaid
flowchart TD
    ...
