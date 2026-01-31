/**
 * WHITE-BOX TESTING TECHNIQUES - 5 KỸ THUẬT KIỂM THỬ HỘP TRẮNG
 * ==============================================================
 * 1. Loop Testing (Kiểm thử vòng lặp)
 * 2. Control Flow Testing (Kiểm thử luồng điều khiển)
 * 3. Data Flow Testing (Kiểm thử luồng dữ liệu)
 * 4. Basis Path Testing (Kiểm thử đường đi cơ sở)
 * 5. Mutation Testing (Kiểm thử đột biến)
 */

// ================================================================
// 1. LOOP TESTING - KIỂM THỬ VÒNG LẶP
// ================================================================
class LoopTesting {
    /**
     * Tính tổng các phần tử trong mảng
     * Vòng lặp: for (int i = 0; i < arr.length; i++)
     * 
     * Test cases cần thiết:
     * - Bỏ qua vòng lặp (0 lần): mảng rỗng
     * - 1 lần lặp: mảng 1 phần tử
     * - 2 lần lặp: mảng 2 phần tử
     * - n lần lặp: mảng nhiều phần tử
     */
    static int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}

// ================================================================
// 2. CONTROL FLOW TESTING - KIỂM THỬ LUỒNG ĐIỀU KHIỂN
// ================================================================
class ControlFlowTesting {
    /**
     * Phân loại số: âm, không, dương, chẵn/lẻ
     * 
     * Control Flow Graph (CFG):
     * [Start]
     * |
     * [n < 0?]--Yes--> return "Âm"
     * |No
     * [n == 0?]--Yes--> return "Không"
     * |No
     * [n % 2 == 0?]--Yes--> return "Dương chẵn"
     * |No
     * return "Dương lẻ"
     */
    static String classifyNumber(int n) {
        if (n < 0) {
            return "Âm";
        }
        if (n == 0) {
            return "Không";
        }
        if (n % 2 == 0) {
            return "Dương chẵn";
        }
        return "Dương lẻ";
    }
}

// ================================================================
// 3. DATA FLOW TESTING - KIỂM THỬ LUỒNG DỮ LIỆU
// ================================================================
class DataFlowTesting {
    /**
     * Tính giá sau giảm giá
     * 
     * Phân tích Data Flow (def-use):
     * - price: def tại dòng param, use tại dòng discount và return
     * - percent: def tại dòng param, use tại dòng discount
     * - discount: def tại dòng tính, use tại dòng return
     * 
     * Đường def-use cần test:
     * - price: định nghĩa → sử dụng trong tính discount
     * - price: định nghĩa → sử dụng trong return
     * - discount: định nghĩa → sử dụng trong return
     */
    static double calcFinalPrice(double price, double percent) {
        double discount = price * percent / 100; // def discount, use price, percent
        return price - discount; // use price, discount
    }
}

// ================================================================
// 4. BASIS PATH TESTING - KIỂM THỬ ĐƯỜNG ĐI CƠ SỞ
// ================================================================
class BasisPathTesting {
    /**
     * Kiểm tra tam giác
     * 
     * Cyclomatic Complexity V(G) = E - N + 2 = 4
     * (hoặc = số điều kiện + 1 = 3 + 1 = 4)
     * 
     * 4 Đường đi cơ sở (Basis Paths):
     * Path 1: a==b && b==c → "Đều"
     * Path 2: a==b || b==c || a==c (không đều) → "Cân"
     * Path 3: không cân → "Thường"
     * Path 4: không hợp lệ → "Không hợp lệ"
     */
    static String triangleType(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "Không hợp lệ";
        }
        if (a + b <= c || b + c <= a || a + c <= b) {
            return "Không hợp lệ";
        }
        if (a == b && b == c) {
            return "Đều";
        }
        if (a == b || b == c || a == c) {
            return "Cân";
        }
        return "Thường";
    }
}

// ================================================================
// 5. MUTATION TESTING - KIỂM THỬ ĐỘT BIẾN
// ================================================================
class MutationTesting {
    /**
     * Hàm gốc: Kiểm tra số dương chẵn
     */
    static boolean isPositiveEven(int n) {
        return n > 0 && n % 2 == 0;
    }

    // --- CÁC MUTANT (bản đột biến) ---

    // Mutant 1: Thay > thành >= (biên)
    static boolean mutant1(int n) {
        return n >= 0 && n % 2 == 0; // BUG: 0 sẽ trả về true
    }

    // Mutant 2: Thay && thành || (toán tử logic)
    static boolean mutant2(int n) {
        return n > 0 || n % 2 == 0; // BUG: -2 sẽ trả về true
    }

    // Mutant 3: Thay == thành != (so sánh)
    static boolean mutant3(int n) {
        return n > 0 && n % 2 != 0; // BUG: trả về số lẻ thay vì chẵn
    }

    // Mutant 4: Thay 2 thành 3 (hằng số)
    static boolean mutant4(int n) {
        return n > 0 && n % 3 == 0; // BUG: kiểm tra chia hết cho 3
    }
}

// ================================================================
// TEST CLASS - KIỂM THỬ TẤT CẢ 5 KỸ THUẬT
// ================================================================
class TestWhiteBox {
    static int passed = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     WHITE-BOX TESTING - 5 KỸ THUẬT KIỂM THỬ HỘP TRẮNG     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Nếu có tham số, chạy test cụ thể
        if (args.length > 0) {
            switch (args[0]) {
                case "1":
                    testLoopTesting();
                    break;
                case "2":
                    testControlFlowTesting();
                    break;
                case "3":
                    testDataFlowTesting();
                    break;
                case "4":
                    testBasisPathTesting();
                    break;
                case "5":
                    testMutationTesting();
                    break;
                default:
                    System.out.println("Sử dụng: java TestWhiteBox [1-5]");
                    System.out.println("  1 - Loop Testing");
                    System.out.println("  2 - Control Flow Testing");
                    System.out.println("  3 - Data Flow Testing");
                    System.out.println("  4 - Basis Path Testing");
                    System.out.println("  5 - Mutation Testing");
                    return;
            }
        } else {
            // Không có tham số → chạy tất cả
            testLoopTesting();
            testControlFlowTesting();
            testDataFlowTesting();
            testBasisPathTesting();
            testMutationTesting();
        }

        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("TỔNG KẾT: " + passed + "/" + total + " tests PASSED");
        System.out.println("════════════════════════════════════════════════════════════");
    }

    // ============== 1. TEST LOOP TESTING ==============
    static void testLoopTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. LOOP TESTING - Kiểm thử vòng lặp                        │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        // Test 1: Bỏ qua vòng lặp (0 lần lặp)
        check("Loop 0 lần (mảng rỗng)",
                LoopTesting.sumArray(new int[] {}), 0);

        // Test 2: 1 lần lặp
        check("Loop 1 lần",
                LoopTesting.sumArray(new int[] { 5 }), 5);

        // Test 3: 2 lần lặp
        check("Loop 2 lần",
                LoopTesting.sumArray(new int[] { 3, 7 }), 10);

        // Test 4: Nhiều lần lặp
        check("Loop n lần (5 phần tử)",
                LoopTesting.sumArray(new int[] { 1, 2, 3, 4, 5 }), 15);

        System.out.println();
    }

    // ============== 2. TEST CONTROL FLOW ==============
    static void testControlFlowTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 2. CONTROL FLOW TESTING - Kiểm thử luồng điều khiển        │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        // Test từng nhánh trong CFG
        check("Nhánh 1: số âm",
                ControlFlowTesting.classifyNumber(-5), "Âm");

        check("Nhánh 2: số không",
                ControlFlowTesting.classifyNumber(0), "Không");

        check("Nhánh 3: dương chẵn",
                ControlFlowTesting.classifyNumber(4), "Dương chẵn");

        check("Nhánh 4: dương lẻ",
                ControlFlowTesting.classifyNumber(7), "Dương lẻ");

        System.out.println();
    }

    // ============== 3. TEST DATA FLOW ==============
    static void testDataFlowTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 3. DATA FLOW TESTING - Kiểm thử luồng dữ liệu              │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        // Test các đường def-use
        check("def-use price (100, 10%)",
                DataFlowTesting.calcFinalPrice(100, 10), 90.0);

        check("def-use với discount = 0",
                DataFlowTesting.calcFinalPrice(100, 0), 100.0);

        check("def-use với discount = 100%",
                DataFlowTesting.calcFinalPrice(100, 100), 0.0);

        check("def-use price = 0",
                DataFlowTesting.calcFinalPrice(0, 50), 0.0);

        System.out.println();
    }

    // ============== 4. TEST BASIS PATH ==============
    static void testBasisPathTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 4. BASIS PATH TESTING - Kiểm thử đường đi cơ sở            │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println("   Cyclomatic Complexity V(G) = 4 → 4 đường đi cơ sở");

        // 4 đường đi cơ sở
        check("Path 1: Tam giác đều (3,3,3)",
                BasisPathTesting.triangleType(3, 3, 3), "Đều");

        check("Path 2: Tam giác cân (3,3,4)",
                BasisPathTesting.triangleType(3, 3, 4), "Cân");

        check("Path 3: Tam giác thường (3,4,5)",
                BasisPathTesting.triangleType(3, 4, 5), "Thường");

        check("Path 4: Không hợp lệ (0,1,2)",
                BasisPathTesting.triangleType(0, 1, 2), "Không hợp lệ");

        System.out.println();
    }

    // ============== 5. TEST MUTATION ==============
    static void testMutationTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 5. MUTATION TESTING - Kiểm thử đột biến                    │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println("   Mục tiêu: Tìm test case 'giết' được các mutant\n");

        // Test case để giết các mutant
        System.out.println("   Hàm gốc: n > 0 && n % 2 == 0");

        // Test với n = 4 (dương chẵn - kết quả đúng)
        boolean original = MutationTesting.isPositiveEven(4);
        check("Gốc: isPositiveEven(4) = true", original, true);

        // Test giết Mutant 1: n >= 0 (thay vì n > 0)
        // n = 0 sẽ phân biệt: gốc = false, mutant1 = true
        System.out.println("\n   Mutant 1: n >= 0 && n % 2 == 0 (thay > thành >=)");
        check("Giết M1: n=0, gốc=false", MutationTesting.isPositiveEven(0), false);
        check("Giết M1: n=0, mutant=true", MutationTesting.mutant1(0), true);
        System.out.println("   → Mutant 1 bị GIẾT! ✗");

        // Test giết Mutant 2: || thay vì &&
        // n = -2 sẽ phân biệt: gốc = false, mutant2 = true
        System.out.println("\n   Mutant 2: n > 0 || n % 2 == 0 (thay && thành ||)");
        check("Giết M2: n=-2, gốc=false", MutationTesting.isPositiveEven(-2), false);
        check("Giết M2: n=-2, mutant=true", MutationTesting.mutant2(-2), true);
        System.out.println("   → Mutant 2 bị GIẾT! ✗");

        // Test giết Mutant 3: != thay vì ==
        // n = 4 sẽ phân biệt: gốc = true, mutant3 = false
        System.out.println("\n   Mutant 3: n > 0 && n % 2 != 0 (thay == thành !=)");
        check("Giết M3: n=4, gốc=true", MutationTesting.isPositiveEven(4), true);
        check("Giết M3: n=4, mutant=false", MutationTesting.mutant3(4), false);
        System.out.println("   → Mutant 3 bị GIẾT! ✗");

        // Test giết Mutant 4: % 3 thay vì % 2
        // n = 4 sẽ phân biệt: gốc = true, mutant4 = false
        System.out.println("\n   Mutant 4: n > 0 && n % 3 == 0 (thay 2 thành 3)");
        check("Giết M4: n=4, gốc=true", MutationTesting.isPositiveEven(4), true);
        check("Giết M4: n=4, mutant=false", MutationTesting.mutant4(4), false);
        System.out.println("   → Mutant 4 bị GIẾT! ✗");

        System.out.println("\n   ★ Mutation Score = 4/4 = 100% (tất cả mutant bị giết)");
    }

    // ============== HELPER METHODS ==============
    static void check(String name, int actual, int expected) {
        total++;
        if (actual == expected) {
            passed++;
            System.out.println("   ✓ " + name);
        } else {
            System.out.println("   ✗ " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }

    static void check(String name, double actual, double expected) {
        total++;
        if (Math.abs(actual - expected) < 0.001) {
            passed++;
            System.out.println("   ✓ " + name);
        } else {
            System.out.println("   ✗ " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }

    static void check(String name, String actual, String expected) {
        total++;
        if (actual.equals(expected)) {
            passed++;
            System.out.println("   ✓ " + name);
        } else {
            System.out.println("   ✗ " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }

    static void check(String name, boolean actual, boolean expected) {
        total++;
        if (actual == expected) {
            passed++;
            System.out.println("   ✓ " + name);
        } else {
            System.out.println("   ✗ " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }
}
