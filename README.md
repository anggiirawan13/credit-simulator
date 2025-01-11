# **Credit Simulator Application**
**Credit Simulator** adalah aplikasi berbasis Java yang digunakan untuk mensimulasikan perhitungan cicilan kredit berdasarkan jenis kendaraan, kondisi kendaraan, jumlah pinjaman, uang muka, dan tenor pinjaman. Project ini mengadopsi pendekatan modular dengan pola desain MVC (Model-View-Controller) dan mendukung berbagai cara input, seperti input manual, melalui file, maupun data dari mock API.

---

## **Struktur Project**
```text
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.simulator.credit
│   │   │       ├── Main.java
│   │   │       ├── controller
│   │   │       │   └── LoanController.java     
│   │   │       ├── model
│   │   │       │   ├── LoanCalculator.java
│   │   │       │   ├── LoanModel.java
│   │   │       │   └── LoanValidation.java
│   │   │       │   └── NullEmptyChecker.java
│   │   │       └── view
│   │   │           └── LoanView.java
│   ├── test
│   │   ├── java
│   │   │   └── com.simulator.credit
│   │   │       ├── model
│   │   │       │   ├── LoanCaltulatorTest.java
│   │   │       │   └── LoanValidationTest.java
├── pom.xml
└── README.md
```

---

## **Fitur**
1. **Manual Input**:  
   Kalkulasi kredit dengan memasukan detail melalui terminal/console.
2. **TXT File Input**:  
   Kalkulasi kredit menggunakan file txt.
   ```text
   HEADER,VEHICLE TYPE,VEHICLE CONDITION,VEHICLE YEAR,LOAN AMOUNT,LOAN PERIOD,DOWN PAYMENT AMOUNT
   VALUE,MOTOR,BARU,2024,100000000,3,25000000
   ```
3. **Simulasi API**:  
   Load detail melalui API.
   ```json
   {
        . . .
        "result": [
            {
                "vehicleType": "MOTOR",
                "vehicleCondition": "BARU",
                "vehicleYear": "2024",
                "loanAmount": "100000000",
                "loanPeriod": "3",
                "downPaymentAmount": "25000000"
            }
        ]
        . . .
   }
4. **Unit Testing**:  
   Menggunakan dependency **JUnit 5** dan **Mockito** untuk melakukan unit test.

---

## **Cara Menggunakan**

### **Persyaratan Sistem**
- Java Development Kit (JDK) 17.0.11 atau lebih tinggi.
- Maven 3.9.9 atau lebih tinggi.

### **Menjalankan Aplikasi**
1. **Clone repository**:
   ```bash
   git clone https://github.com/anggiirawan13/credit-simulator.git
   cd credit-simulator
   ```

2. **Build project menggunakan Maven**:
   ```bash
   mvn clean package
   ```

3. **Jalankan aplikasi**:
   ```bash
   mvn -q exec:java -Dexec.mainClass=com.simulator.credit.Main
   ```

4. **Sesuaikan dengan menu yang ditampilkan**:
    - **1**: Input detail melalui terminal/console.
    - **2**: Input detail dari file txt.
    - **3**: Load data dari API.
    - **4**: Keluar dari aplikasi.

---

### **Menjalankan Aplikasi Menggunakan File Eksekusi**

1. **Linux/MacOS**:
  - Tanpa file
  ```bash
  ./credit_simulator
  ```
  - Dengan file
  ```text
  FORMAT FILE
  /path/your/file/file_name.txt
  ```
  ```bash
  # CONTOH 1
  ./credit_simulator file_inputs.txt
  ```
2.**Windows**:
- Tanpa file
  ```cmd
  credit_simulator.bat
  ```
- Dengan file
  ```cmd
  credit_simulator.bat file_inputs.txt
  ```

### **Menjalankan File JAR**
Setelah build selesai, file JAR akan berada di dalam direktori `target/`.:

```bash
java -jar target/credit-simulator-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## **Unit Testing**
### **Menjalankan Test**
Untuk menjalankan semua test:
```bash
mvn test
```

### **Daftar Test**
- **LoanValidationTest.java**:  
  Menguji semua aturan wajib kredit.
- **LoanCalculatorTest.java**:  
  Menguji rumus kredit.

---

## Docker
Execute file `docker_local` jika ingin menjalankan aplikasi di docker dengan command:
```bash
./docker_local
```

---

## **Dependensi**
Dependensi yang digunakan:

```xml
<dependencies>
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version>
    </dependency>

    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>4.5.1</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>4.5.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---


### CATATAN
- Jika saat menjalankan suatu command terjadi error, jalankan command ini dengan aturan berikut:
1. **WAJIB** berada di dalam folder project **credit-simulator**
```bash
sudo chmod -R 777 * 
```