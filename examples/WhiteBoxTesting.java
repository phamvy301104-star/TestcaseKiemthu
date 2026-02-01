/**
 * WHITE-BOX TESTING TECHNIQUES - 5 KY THUAT KIEM THU HOP TRANG
 * ==============================================================
 * 1. Loop Testing (Kiem thu vong lap)
 * 2. Control Flow Testing (Kiem thu luong dieu khien)
 * 3. Data Flow Testing (Kiem thu luong du lieu)
 * 4. Basis Path Testing (Kiem thu duong di co so)
 * 5. Mutation Testing (Kiem thu dot bien)
 */

// ================================================================
// 1. LOOP TESTING - KIEM THU VONG LAP
// ================================================================
class LoopTesting {
    /**
     * Tinh tong cac phan tu trong mang
     * Vong lap: for (int i = 0; i < arr.length; i++)
     * 
     * Test cases can thiet:
     * - Bo qua vong lap (0 lan): mang rong
     * - 1 lan lap: mang 1 phan tu
     * - 2 lan lap: mang 2 phan tu
     * - n lan lap: mang nhieu phan tu
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
// 2. CONTROL FLOW TESTING - KIEM THU LUONG DIEU KHIEN
// ================================================================
class ControlFlowTesting {
    /**
     * Phan loai so: am, khong, duong, chan/le
     * 
     * Control Flow Graph (CFG):
     * [Start]
     * |
     * [n < 0?]--Yes--> return "Am"
     * |No
     * [n == 0?]--Yes--> return "Khong"
     * |No
     * [n % 2 == 0?]--Yes--> return "Duong chan"
     * |No
     * return "Duong le"
     */
    static String classifyNumber(int n) {
        if (n < 0) {
            return "Am";
        }
        if (n == 0) {
            return "Khong";
        }
        if (n % 2 == 0) {
            return "Duong chan";
        }
        return "Duong le";
    }
}

// ================================================================
// 3. DATA FLOW TESTING - KIEM THU LUONG DU LIEU
// ================================================================
class DataFlowTesting {
    /**
     * Tinh gia sau giam gia
     * 
     * Phan tich Data Flow (def-use):
     * - price: def tai dong param, use tai dong discount va return
     * - percent: def tai dong param, use tai dong discount
     * - discount: def tai dong tinh, use tai dong return
     * 
     * Duong def-use can test:
     * - price: dinh nghia -> su dung trong tinh discount
     * - price: dinh nghia -> su dung trong return
     * - discount: dinh nghia -> su dung trong return
     */
    static double calcFinalPrice(double price, double percent) {
        double discount = price * percent / 100; // def discount, use price, percent
        return price - discount; // use price, discount
    }
}

// ================================================================
// 4. BASIS PATH TESTING - KIEM THU DUONG DI CO SO
// ================================================================
class BasisPathTesting {
    /**
     * Kiem tra tam giac
     * 
     * Cyclomatic Complexity V(G) = E - N + 2 = 4
     * (hoac = so dieu kien + 1 = 3 + 1 = 4)
     * 
     * 4 Duong di co so (Basis Paths):
     * Path 1: a==b && b==c -> "Deu"
     * Path 2: a==b || b==c || a==c (khong deu) -> "Can"
     * Path 3: khong can -> "Thuong"
     * Path 4: khong hop le -> "Khong hop le"
     */
    static String triangleType(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return "Khong hop le";
        }
        if (a + b <= c || b + c <= a || a + c <= b) {
            return "Khong hop le";
        }
        if (a == b && b == c) {
            return "Deu";
        }
        if (a == b || b == c || a == c) {
            return "Can";
        }
        return "Thuong";
    }
}

// ================================================================
// 5. MUTATION TESTING - KIEM THU DOT BIEN
// ================================================================
class MutationTesting {
    /**
     * Ham goc: Kiem tra so duong chan
     */
    static boolean isPositiveEven(int n) {
        return n > 0 && n % 2 == 0;
    }

    // --- CAC MUTANT (ban dot bien) ---

    // Mutant 1: Thay > thanh >= (bien)
    static boolean mutant1(int n) {
        return n >= 0 && n % 2 == 0; // BUG: 0 se tra ve true
    }

    // Mutant 2: Thay && thanh || (toan tu logic)
    static boolean mutant2(int n) {
        return n > 0 || n % 2 == 0; // BUG: -2 se tra ve true
    }

    // Mutant 3: Thay == thanh != (so sanh)
    static boolean mutant3(int n) {
        return n > 0 && n % 2 != 0; // BUG: tra ve so le thay vi chan
    }

    // Mutant 4: Thay 2 thanh 3 (hang so)
    static boolean mutant4(int n) {
        return n > 0 && n % 3 == 0; // BUG: kiem tra chia het cho 3
    }
}

// ================================================================
// TEST CLASS - KIEM THU TAT CA 5 KY THUAT
// ================================================================
class TestWhiteBox {
    static int passed = 0;
    static int total = 0;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     WHITE-BOX TESTING - 5 KY THUAT KIEM THU HOP TRANG     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Neu co tham so, chay test cu the
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
                    System.out.println("Su dung: java TestWhiteBox [1-5]");
                    System.out.println("  1 - Loop Testing");
                    System.out.println("  2 - Control Flow Testing");
                    System.out.println("  3 - Data Flow Testing");
                    System.out.println("  4 - Basis Path Testing");
                    System.out.println("  5 - Mutation Testing");
                    return;
            }
        } else {
            // Khong co tham so -> chay tat ca
            testLoopTesting();
            testControlFlowTesting();
            testDataFlowTesting();
            testBasisPathTesting();
            testMutationTesting();
        }

        System.out.println("\n════════════════════════════════════════════════════════════");
        System.out.println("TONG KET: " + passed + "/" + total + " tests PASSED");
        System.out.println("════════════════════════════════════════════════════════════");
    }

    // ============== 1. TEST LOOP TESTING ==============
    static void testLoopTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. LOOP TESTING - Kiem thu vong lap                        │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        // Test 1: Bo qua vong lap (0 lan lap)
        check("Loop 0 lan (mang rong)",
                LoopTesting.sumArray(new int[] {}), 0);

        // Test 2: 1 lan lap
        check("Loop 1 lan",
                LoopTesting.sumArray(new int[] { 5 }), 5);

        // Test 3: 2 lan lap
        check("Loop 2 lan",
                LoopTesting.sumArray(new int[] { 3, 7 }), 10);

        // Test 4: Nhieu lan lap
        check("Loop n lan (5 phan tu)",
                LoopTesting.sumArray(new int[] { 1, 2, 3, 4, 5 }), 15);

        System.out.println();
    }

    // ============== 2. TEST CONTROL FLOW ==============
    static void testControlFlowTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 2. CONTROL FLOW TESTING - Kiem thu luong dieu khien        │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        // Test tung nhanh trong CFG
        check("Nhanh 1: so am",
                ControlFlowTesting.classifyNumber(-5), "Am");

        check("Nhanh 2: so khong",
                ControlFlowTesting.classifyNumber(0), "Khong");

        check("Nhanh 3: duong chan",
                ControlFlowTesting.classifyNumber(4), "Duong chan");

        check("Nhanh 4: duong le",
                ControlFlowTesting.classifyNumber(7), "Duong le");

        System.out.println();
    }

    // ============== 3. TEST DATA FLOW ==============
    static void testDataFlowTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 3. DATA FLOW TESTING - Kiem thu luong du lieu              │");
        System.out.println("└────────────────────────────────────────────────────────────┘");

        // Test cac duong def-use
        check("def-use price (100, 10%)",
                DataFlowTesting.calcFinalPrice(100, 10), 90.0);

        check("def-use voi discount = 0",
                DataFlowTesting.calcFinalPrice(100, 0), 100.0);

        check("def-use voi discount = 100%",
                DataFlowTesting.calcFinalPrice(100, 100), 0.0);

        check("def-use price = 0",
                DataFlowTesting.calcFinalPrice(0, 50), 0.0);

        System.out.println();
    }

    // ============== 4. TEST BASIS PATH ==============
    static void testBasisPathTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 4. BASIS PATH TESTING - Kiem thu duong di co so            │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println("   Cyclomatic Complexity V(G) = 4 -> 4 duong di co so");

        // 4 duong di co so
        check("Path 1: Tam giac deu (3,3,3)",
                BasisPathTesting.triangleType(3, 3, 3), "Deu");

        check("Path 2: Tam giac can (3,3,4)",
                BasisPathTesting.triangleType(3, 3, 4), "Can");

        check("Path 3: Tam giac thuong (3,4,5)",
                BasisPathTesting.triangleType(3, 4, 5), "Thuong");

        check("Path 4: Khong hop le (0,1,2)",
                BasisPathTesting.triangleType(0, 1, 2), "Khong hop le");

        System.out.println();
    }

    // ============== 5. TEST MUTATION ==============
    static void testMutationTesting() {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│ 5. MUTATION TESTING - Kiem thu dot bien                    │");
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println("   Muc tieu: Tim test case 'giet' duoc cac mutant\n");

        // Test case de giet cac mutant
        System.out.println("   Ham goc: n > 0 && n % 2 == 0");

        // Test voi n = 4 (duong chan - ket qua dung)
        boolean original = MutationTesting.isPositiveEven(4);
        check("Goc: isPositiveEven(4) = true", original, true);

        // Test giet Mutant 1: n >= 0 (thay vi n > 0)
        // n = 0 se phan biet: goc = false, mutant1 = true
        System.out.println("\n   Mutant 1: n >= 0 && n % 2 == 0 (thay > thanh >=)");
        check("Giet M1: n=0, goc=false", MutationTesting.isPositiveEven(0), false);
        check("Giet M1: n=0, mutant=true", MutationTesting.mutant1(0), true);
        System.out.println("   -> Mutant 1 bi GIET! X");

        // Test giet Mutant 2: || thay vi &&
        // n = -2 se phan biet: goc = false, mutant2 = true
        System.out.println("\n   Mutant 2: n > 0 || n % 2 == 0 (thay && thanh ||)");
        check("Giet M2: n=-2, goc=false", MutationTesting.isPositiveEven(-2), false);
        check("Giet M2: n=-2, mutant=true", MutationTesting.mutant2(-2), true);
        System.out.println("   -> Mutant 2 bi GIET! X");

        // Test giet Mutant 3: != thay vi ==
        // n = 4 se phan biet: goc = true, mutant3 = false
        System.out.println("\n   Mutant 3: n > 0 && n % 2 != 0 (thay == thanh !=)");
        check("Giet M3: n=4, goc=true", MutationTesting.isPositiveEven(4), true);
        check("Giet M3: n=4, mutant=false", MutationTesting.mutant3(4), false);
        System.out.println("   -> Mutant 3 bi GIET! X");

        // Test giet Mutant 4: % 3 thay vi % 2
        // n = 4 se phan biet: goc = true, mutant4 = false
        System.out.println("\n   Mutant 4: n > 0 && n % 3 == 0 (thay 2 thanh 3)");
        check("Giet M4: n=4, goc=true", MutationTesting.isPositiveEven(4), true);
        check("Giet M4: n=4, mutant=false", MutationTesting.mutant4(4), false);
        System.out.println("   -> Mutant 4 bi GIET! X");

        System.out.println("\n   * Mutation Score = 4/4 = 100% (tat ca mutant bi giet)");
    }

    // ============== HELPER METHODS ==============
    static void check(String name, int actual, int expected) {
        total++;
        if (actual == expected) {
            passed++;
            System.out.println("   [PASS] " + name);
        } else {
            System.out.println("   [FAIL] " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }

    static void check(String name, double actual, double expected) {
        total++;
        if (Math.abs(actual - expected) < 0.001) {
            passed++;
            System.out.println("   [PASS] " + name);
        } else {
            System.out.println("   [FAIL] " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }

    static void check(String name, String actual, String expected) {
        total++;
        if (actual.equals(expected)) {
            passed++;
            System.out.println("   [PASS] " + name);
        } else {
            System.out.println("   [FAIL] " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }

    static void check(String name, boolean actual, boolean expected) {
        total++;
        if (actual == expected) {
            passed++;
            System.out.println("   [PASS] " + name);
        } else {
            System.out.println("   [FAIL] " + name + " (got: " + actual + ", expected: " + expected + ")");
        }
    }
}
