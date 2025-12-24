# Hibernate ORM with PostgreSQL (Maven Project)

This project demonstrates a **core Hibernate ORM setup** using **Maven**, **PostgreSQL**, and **Java**, including:

* Entity mapping
* Database connection using environment variables
* Saving data using Hibernate Session
* Running multiple main classes using Maven profiles

---

## 📌 Tech Stack

* **Java** (JDK 8+)
* **Hibernate ORM 6.x**
* **PostgreSQL 17**
* **Maven**
* **pgAdmin 4**
* **dotenv-java** (for environment variables)

---

## 📁 Project Structure

```
hibernate_example
│
├── src/main/java
│   └── com/example/hibernate_example
│       ├── App.java
│       ├── RelationshipExApp.java
│       └── Model
│           ├── Student.java
│           └── Laptop.java
│
├── src/main/resources
│   └── hibernate.cfg.xml
│
├── .env
├── .gitignore
├── pom.xml
└── README.md
```

---

## ⚙️ Database Configuration

This project uses **PostgreSQL**.

### 1️⃣ Create Database

```sql
CREATE DATABASE hibernate_db;
```

---

### 2️⃣ Environment Variables (`.env` file)

Create a `.env` file in the **project root**:

```env
DB_URL=jdbc:postgresql://localhost:5432/hibernate_db
DB_USERNAME=postgres
DB_PASSWORD=your_password_here
```

🔐 **Security Note:**
`.env` file is added to `.gitignore` to avoid exposing credentials.

---

## 🗄 Hibernate Configuration

`hibernate.cfg.xml` contains basic Hibernate settings (without credentials):

```xml
<property name="hibernate.hbm2ddl.auto">update</property>
<property name="hibernate.show_sql">true</property>
<property name="hibernate.format_sql">true</property>
```

Database credentials are loaded **dynamically** using dotenv in Java code.

---

## 🚀 Running the Project

### 1️⃣ Compile the Project

```bash
mvn clean compile
```

---

### 2️⃣ Run Using Maven Profile

This project supports **multiple runnable classes** using Maven profiles.

Example:

```bash
mvn exec:java -PRelation
```

---

### 3️⃣ Sample Output

```text
Saved Successfully
```

Tables will be created automatically:

* `student`
* `laptop`

---

## 📊 View Data in pgAdmin

1. Open **pgAdmin**
2. Navigate:

   ```
   Servers → Databases → hibernate_db → Schemas → public → Tables
   ```
3. Right-click table → **View/Edit Data → All Rows**

Or use SQL:

```sql
SELECT * FROM student;
SELECT * FROM laptop;
```

---

## ⚠️ Important Notes

### 🔹 Deprecated `session.save()`

Hibernate 6 shows `save()` as deprecated.

Recommended alternative:

```java
session.persist(entity);
```

---

### 🔹 Thread Warnings on Exit

Warnings like:

```text
Thread did not finish despite interruption
```

are **normal** when using Hibernate + PostgreSQL JDBC in non-production apps.

---

## 🛠 Common Issues & Fixes

### ❌ `psql is not recognized`

✔ Add PostgreSQL `bin` directory to **System PATH**

---

### ❌ Java version error (`source 7 not supported`)

✔ Update `pom.xml`:

```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

---

## 🎯 Learning Outcomes

* Core Hibernate ORM setup
* PostgreSQL integration
* Environment-based configuration
* Maven profiles for multiple main classes
* Entity persistence using Hibernate Session

---

## 👨‍💻 Author

**Mansi**
MCA | Java | Hibernate | PostgreSQL
Prepared for **hands-on practice**

---
