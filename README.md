# simple-chat-apps
source code ini merupakan rest api aplikasi chat sederhana dengan menggunakan bahasa java dan framework spring boot.

# requirements
1. Java 11
2. Maven
3. Code editor (Intellij / Eclipse)
4. MySQL
5. ActiveMQ

# Installation
1. Start MySQL (3306) and ActiveMQ (61616)
2. Build source code dengan menggunakan command:
```
mvn clean install
```
maka akan tergenerate 1 database dan 4 table didalamnya: <br />
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

Keterangan:
Sedikit berbeda ketika membuat pesan baru, dalam menjawab pesan masuk sudah FE wajib mengirimkan request room id yang didapat ketika melihat daftar list incoming message

