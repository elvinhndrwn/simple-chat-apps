# simple-chat-apps
source code ini merupakan rest api aplikasi chat sederhana dengan menggunakan bahasa java dan framework spring boot.
![design](https://user-images.githubusercontent.com/61193419/210915903-53352d9b-5470-4950-ae04-5a9eb605f338.png)

<br />

# requirements <br />
1. Java 11 <br />
2. Maven <br /> 
3. Code editor (Intellij / Eclipse) <br />
4. MySQL <br /> 
5. ActiveMQ <br />

# Installation
1. Start MySQL (3306) and ActiveMQ (61616)
2. Build source code dengan menggunakan command:
```
mvn clean install
```
maka akan tergenerate 1 database (chat-app) dan 4 table didalamnya: <br />
![image](https://user-images.githubusercontent.com/61193419/210861588-b44b1c51-7046-4807-b1a7-bf8258854b94.png)

3. Lalu running aplikasi menggunakan command:
```
mvn spring-boot:run
```

# documentation API
1. Untuk mengirim pesan, memerlukan registrasi terlebih dahulu dengan menggunakan request:
![image](https://user-images.githubusercontent.com/61193419/210862429-328d5950-0d1f-409a-8756-dc564fc450a1.png)

2. Lalu login dengan menggunakan request:
![image](https://user-images.githubusercontent.com/61193419/210862648-f08a7715-8736-4e84-9e63-5296c7836a67.png)

3. Agar bisa saling mengirim pesan diperlukan 2 User yang terdaftar
4. Kirim pesan baru 
![image](https://user-images.githubusercontent.com/61193419/210863042-e444718b-10a9-45e0-9dd7-4365b5807ee8.png)

Keterangan: <br />
a. senderUserId = id user pengirim yang didapat ketika login <br />
b. phoneNumber = nomor hp tujuan <br />
c. message = pesan chat yang dikirim <br />

5. Menjawab pesan/percakapan
![image](https://user-images.githubusercontent.com/61193419/210863822-54dce067-8199-4058-979c-6f0a98b8c93c.png)

Keterangan: <br />
Sedikit berbeda ketika membuat pesan baru, dalam menjawab pesan masuk FE wajib mengirimkan request room id yang didapat ketika melihat daftar list incoming message

6. Mengambil data list incoming/outgoing message
![image](https://user-images.githubusercontent.com/61193419/210914944-644cb19c-0886-4425-a158-548be099909c.png)

Keterangan : <br />
1. parameter id = user id yang didapat ketika login <br />
2. response array sudah diurutkan berdasarkan time (waktu kirim pesan) dari yang terbaru - terlama. <br />

7. Menampilkan / membaca pesan
![image](https://user-images.githubusercontent.com/61193419/210915409-e6bfdb1d-2ee1-43cd-b071-ded9449cb3ce.png)

Keterangan: <br />
1. service ini digunakan ketika melihat/membaca detail pesan masuk yang sebelum nya tampil pada halaman list incoming/outgoing message <br />
2. pada url terdapat parameter .../2/1 yang merupakan: <br />
    a. 2 = user id (didapat ketika login) <br />
    b. 1 = room id (didapat ketika di halaman list incoming/outgoing message) <br />
    c. contactName dan phoneNumber adalah nama dan nomor HP tujuan <br />
    d. messages adalah list pesan masuk dan keluar <br />

Terimakasih.
 
