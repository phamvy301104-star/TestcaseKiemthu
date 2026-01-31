# 📚 HƯỚNG DẪN CHI TIẾT - 5 KỸ THUẬT KIỂM THỬ HỘP TRẮNG

## 🚀 CÁCH CHẠY TEST

### Bước 1: Mở Terminal (PowerShell)
```
Nhấn Ctrl + ` trong VS Code
```

### Bước 2: Di chuyển đến thư mục examples
```powershell
cd "c:\Kiểm Tra và Đảm Bảo Chất Lượng Phần Mệm\Đề tài 22 - Kỹ thuật kiểm thử hộp trắng\examples"
```

### Bước 3: Biên dịch file Java
```powershell
javac WhiteBoxTesting.java
```

### Bước 4: Chạy test

**Chạy tất cả 5 kỹ thuật:**
```powershell
java TestWhiteBox
```

**Chạy từng kỹ thuật riêng:**
```powershell
java TestWhiteBox 1    # Loop Testing (Kiểm thử vòng lặp)
java TestWhiteBox 2    # Control Flow Testing (Kiểm thử luồng điều khiển)
java TestWhiteBox 3    # Data Flow Testing (Kiểm thử luồng dữ liệu)
java TestWhiteBox 4    # Basis Path Testing (Kiểm thử đường đi cơ sở)
java TestWhiteBox 5    # Mutation Testing (Kiểm thử đột biến)
```

---

## ⚠️ LƯU Ý QUAN TRỌNG

1. **Phải ở đúng thư mục `examples`** trước khi chạy lệnh
2. **Phải biên dịch (`javac`)** trước khi chạy (`java`)
3. Nếu gặp lỗi "could not find class", kiểm tra lại đường dẫn

### Lệnh nhanh (copy toàn bộ):
```powershell
cd "c:\Kiểm Tra và Đảm Bảo Chất Lượng Phần Mệm\Đề tài 22 - Kỹ thuật kiểm thử hộp trắng\examples"; javac WhiteBoxTesting.java; java TestWhiteBox
```

---

## 📖 GIẢI THÍCH 5 KỸ THUẬT

### 1️⃣ LOOP TESTING (Kiểm thử vòng lặp)

**Định nghĩa:** Kiểm tra vòng lặp hoạt động đúng ở các trường hợp biên.

**Hàm test:** `sumArray(int[] arr)` - Tính tổng mảng

**Test cases:**
| # | Input | Số lần lặp | Expected | Mục đích |
|---|-------|------------|----------|----------|
| 1 | `[]` | 0 lần | 0 | Bỏ qua vòng lặp |
| 2 | `[5]` | 1 lần | 5 | Lặp đúng 1 lần |
| 3 | `[3,7]` | 2 lần | 10 | Phát hiện off-by-one |
| 4 | `[1,2,3,4,5]` | 5 lần | 15 | Lặp nhiều lần |

---

### 2️⃣ CONTROL FLOW TESTING (Kiểm thử luồng điều khiển)

**Định nghĩa:** Đảm bảo test đi qua mọi nhánh trong Control Flow Graph (CFG).

**Hàm test:** `classifyNumber(int n)` - Phân loại số

**Control Flow Graph:**
```
     [Start]
        │
    [n < 0?]───Yes──► "Âm"
        │No
    [n == 0?]──Yes──► "Không"
        │No
  [n % 2 == 0?]─Yes─► "Dương chẵn"
        │No
   "Dương lẻ"
```

**Test cases:**
| # | Input | Nhánh | Expected |
|---|-------|-------|----------|
| 1 | -5 | n < 0 = True | "Âm" |
| 2 | 0 | n == 0 = True | "Không" |
| 3 | 4 | n % 2 == 0 = True | "Dương chẵn" |
| 4 | 7 | Tất cả False | "Dương lẻ" |

---

### 3️⃣ DATA FLOW TESTING (Kiểm thử luồng dữ liệu)

**Định nghĩa:** Theo dõi biến từ **định nghĩa (def)** đến **sử dụng (use)**.

**Hàm test:** `calcFinalPrice(double price, double percent)` - Tính giá sau giảm

**Phân tích Def-Use:**
```java
static double calcFinalPrice(double price, double percent) {
    // DEF: price, percent (từ tham số)
    double discount = price * percent / 100;  // DEF: discount, USE: price, percent
    return price - discount;                   // USE: price, discount
}
```

**Test cases:**
| # | price | percent | Đường def-use | Expected |
|---|-------|---------|---------------|----------|
| 1 | 100 | 10 | price→discount→return | 90.0 |
| 2 | 100 | 0 | discount = 0 | 100.0 |
| 3 | 100 | 100 | discount = price | 0.0 |
| 4 | 0 | 50 | price = 0 | 0.0 |

---

### 4️⃣ BASIS PATH TESTING (Kiểm thử đường đi cơ sở)

**Định nghĩa:** Tính Cyclomatic Complexity V(G) để xác định số đường đi tối thiểu cần test.

**Công thức:**
```
V(G) = E - N + 2   hoặc   V(G) = Số điều kiện + 1
```

**Hàm test:** `triangleType(int a, int b, int c)` - Phân loại tam giác

**Tính toán:**
```
V(G) = 4 điều kiện + 1 = 5 (gộp còn 4 paths)
```

**4 đường đi cơ sở:**
| Path | Input | Điều kiện | Expected |
|------|-------|-----------|----------|
| 1 | (3,3,3) | a==b && b==c | "Đều" |
| 2 | (3,3,4) | a==b hoặc b==c hoặc a==c | "Cân" |
| 3 | (3,4,5) | Không thỏa điều kiện nào | "Thường" |
| 4 | (0,1,2) | Cạnh <= 0 | "Không hợp lệ" |

---

### 5️⃣ MUTATION TESTING (Kiểm thử đột biến)

**Định nghĩa:** Tạo các bản sao có lỗi (mutant), kiểm tra test có phát hiện được lỗi không.

**Hàm gốc:** `isPositiveEven(int n)` - Kiểm tra số dương chẵn
```java
return n > 0 && n % 2 == 0;
```

**Các Mutant:**
| Mutant | Thay đổi | Code bị lỗi |
|--------|----------|-------------|
| M1 | `>` → `>=` | `n >= 0 && n % 2 == 0` |
| M2 | `&&` → `||` | `n > 0 || n % 2 == 0` |
| M3 | `==` → `!=` | `n > 0 && n % 2 != 0` |
| M4 | `2` → `3` | `n > 0 && n % 3 == 0` |

**Test cases giết mutant:**
| Mutant | Test Input | Gốc | Mutant | Kết quả |
|--------|------------|-----|--------|---------|
| M1 | n = 0 | false | true | ✗ KILLED |
| M2 | n = -2 | false | true | ✗ KILLED |
| M3 | n = 4 | true | false | ✗ KILLED |
| M4 | n = 4 | true | false | ✗ KILLED |

**Mutation Score = 4/4 = 100%**

---

## 📊 SO SÁNH 5 KỸ THUẬT

| # | Kỹ thuật | Câu hỏi trả lời | Độ mạnh |
|---|----------|-----------------|---------|
| 1 | Loop Testing | Vòng lặp có chạy đúng không? | ⭐⭐ |
| 2 | Control Flow | Mọi nhánh có được test không? | ⭐⭐⭐ |
| 3 | Data Flow | Biến có được khởi tạo đúng không? | ⭐⭐⭐ |
| 4 | Basis Path | Đã test đủ số đường đi chưa? | ⭐⭐⭐⭐ |
| 5 | Mutation | Test case có đủ mạnh không? | ⭐⭐⭐⭐⭐ |

---

## 🎯 KẾT QUẢ MONG ĐỢI

Khi chạy `java TestWhiteBox`, kết quả:
```
╔════════════════════════════════════════════════════════════╗
║     WHITE-BOX TESTING - 5 KỸ THUẬT KIỂM THỬ HỘP TRẮNG     ║
╚════════════════════════════════════════════════════════════╝

1. LOOP TESTING          → 4/4 tests PASSED
2. CONTROL FLOW TESTING  → 4/4 tests PASSED
3. DATA FLOW TESTING     → 4/4 tests PASSED
4. BASIS PATH TESTING    → 4/4 tests PASSED
5. MUTATION TESTING      → 9/9 tests PASSED

TỔNG KẾT: 25/25 tests PASSED
```
