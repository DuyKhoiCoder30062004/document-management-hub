package com.saigontechnologyintern.document_management;//package com.saigontechnologyintern.document_management;
//
//public class TestCode {
//    int a[] = {1,2,3,4};
//    //matplotlib (library) - python
//    //LaTex x^2+3x=0;
//    //data science , linear g
//
//    //43 - 60
//    //43
//    // -1234 -> trong (-1) * 1234, lam sao tach ra -1 va 1234 roi in ra man hinh?
//    public Object tachSo(int so){
////        System.out.print(-1 + " " + so *-1);
//        return -1 + " " + so *-1;
//    }
//    public int demDigitAmount(int chuSo){
//        //1234 => count = 4;
//        int count=0;
////        int temp=0;
//        while(chuSo != 0){
////            temp = chuSo % 10; //1234  temp = 123, du = 4
//            chuSo /= 10;
//            count+=1;
//        }
//        return count;
//    }
//    /*
//    20. Liệt kê tất cả “ước số” của số nguyên dương n.
//21. Tính tổng tất cả “ước số” của số nguyên dương n.
//22. Tính tích tất cả “ước số” của số nguyên dương n.
//23. Đếm số lượng “ước số” của số nguyên dương n.
//24. Liệt kê tất cả “ước số lẻ” của số nguyên dương n.
//25. Tính tổng tất cả “ước số chẵn” của số nguyên dương n.
//    */
//    public void lietKeDivisors(int chuSo){
//        for(int i=1;i<=chuSo;i++){
//            if(chuSo % i == 0){
//                System.out.print(i + " " + "\n");
//            }
//        }
//    }
//    public void sumDivisors(int chuSo){
//        int sum=0;
//        for(int i=1;i<=chuSo;i++){
//            if(chuSo % i == 0){
//                sum+=i;
//            }
//
//        }
//        System.out.print(sum);
//    }
//    public void countDivisors(int chuSo){
//        int count=0;
//        for(int i=1;i<=chuSo;i++){
//            if(chuSo % i == 0){
//                count++;
//            }
//
//        }
//        System.out.print(count);
//    }
//    public void lietKeDivisors_Odd(int chuSo){
//        for(int i=1;i<=chuSo;i++){
//            if(chuSo % i == 0 && i % 2 == 1){
//                System.out.print(i + " " + "\n");
//            }
//        }
//    }
//    public void sumDivisors_Even(int chuSo){
//        int sum=0;
//        for(int i=1;i<=chuSo;i++){
//            if(chuSo % i == 0 && i % 2 == 0){
//                sum+=i;
//            }
//        }
//        System.out.print(sum);
//    }
//    public static void main(String[] args){
////     */
//        //Luyen code
//        // Amigoscode AWS EC2
//        TestCode tc = new TestCode();
//        //-1456
////        System.out.print(tc.demDigitAmount(123456));
////        for(int i=0;i<4;i++){
////            System.out.print(tc.a[i]);
////        }
//        System.out.print(tc.tachSo(-1456));
//        tc.lietKeDivisors(100);
//        tc.sumDivisors(100);
//        System.out.print("\n");
//        tc.countDivisors(100);
//        System.out.print("\n");
//        tc.lietKeDivisors_Odd(100);
//        tc.sumDivisors_Even(100);
//    }
////    object oriented programming
//}


/*
26. Tính tích tất cả “ước số lẻ” của số nguyên dương n.
27. Đếm số lượng “ước số chẵn” của số nguyên dương n.
28. Cho số nguyên dương n. Tính tổng các ước số nhỏ hơn chính nó.
29. Tìm ước số lẻ lớn nhất của số nguyên dương n. Ví dụ n = 100 ước lẻ
lớn nhất của 100 là 25.
        30. Cho số nguyên dương n. Kiểm tra số dương n có phải là số hoàn thiện
hay không?

 */

public class TestCode{
//    public void product_Odd_Divisor(int chuSo){
//        double product=1;
//        for(int i=1;i<=chuSo;i++){
//            if(chuSo % i == 0 && i % 2 == 1){
//                product*=i;
//            }
//        }
//        System.out.println(product);
//    }
//    //28. Cho số nguyên dương n. Tính tổng các ước số nhỏ hơn chính nó.
    public void divisorLess_Itself(int chuSo){
        int sum=0;
        for(int i=1;i<=chuSo;i++){
            if(chuSo % i == 0){
                sum+=i;
                if(sum > chuSo){
                    break;
                }
            }
        }
        System.out.print(sum);
    }
    public void print_divisor(int chuSo){
        for(int i=1;i<=chuSo;i++){
           if(chuSo % i == 0){
               System.out.print(i + " ");
           }
        }
    }
    public static void main(String[] args){
        TestCode tc = new TestCode();
//        tc.product_Odd_Divisor(100);
        tc.divisorLess_Itself(100);
//        tc.print_divisor(100);
//        System.out.print(1 + 2 + 4 + 5 + 10 + 20 + 25 + 50);
    }
}