# 🏆 Online Auction System

A full-stack web application for online auctions built with Java Servlets, JSP, and MySQL. Users can create auctions, place bids, and manage their auction activities through a modern, responsive interface.

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square&logo=java)
![Servlet](https://img.shields.io/badge/Jakarta-Servlet%205.0-blue?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)

## ✨ Features

- **User Authentication** - Secure login and registration system
- **Create Auctions** - List items with starting bid and end time
- **Real-time Bidding** - Place bids on active auctions
- **User Dashboard** - View your auctions, bids, and notifications
- **Search Functionality** - Find auctions quickly
- **Profile Management** - Update user information
- **Responsive Design** - Modern glassmorphism UI with dark theme

## 🛠️ Technology Stack

| Layer | Technology |
|-------|------------|
| **Frontend** | JSP, HTML5, CSS3 (Glassmorphism) |
| **Backend** | Java 17+, Jakarta Servlet 5.0, JSP 3.0 |
| **Database** | MySQL 8.0 |
| **Build Tool** | Maven |
| **Server** | Apache Tomcat 10+ |

## 📁 Project Structure

```
online-auction-system/
├── src/main/
│   ├── java/com/auction/
│   │   ├── controller/          # Servlets
│   │   │   ├── LoginServlet.java
│   │   │   ├── RegisterServlet.java
│   │   │   ├── CreateAuctionServlet.java
│   │   │   ├── PlaceBidServlet.java
│   │   │   ├── SearchAuctionServlet.java
│   │   │   └── ...
│   │   ├── dao/                 # Data Access Objects
│   │   │   ├── UserDAO.java
│   │   │   ├── AuctionDAO.java
│   │   │   ├── BidDAO.java
│   │   │   └── NotificationDAO.java
│   │   ├── model/               # Entity Classes
│   │   │   ├── User.java
│   │   │   ├── Auction.java
│   │   │   ├── Bid.java
│   │   │   └── Notification.java
│   │   └── util/
│   │       └── DBConnection.java
│   └── webapp/
│       ├── css/                 # Stylesheets
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── index.jsp            # Home page
│       ├── login.jsp            # Login page
│       ├── register.jsp         # Registration
│       ├── dashboard.jsp        # User dashboard
│       ├── createAuction.jsp    # Create auction
│       ├── auctionDetails.jsp   # Auction details
│       └── ...
├── pom.xml
└── README.md
```

## 🗄️ Database Setup

### 1. Create the Database

```sql
CREATE DATABASE auction_db;
USE auction_db;
```

### 2. Create Tables

```sql
-- Users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Auctions table
CREATE TABLE auctions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    starting_bid DECIMAL(10,2) NOT NULL,
    current_bid DECIMAL(10,2),
    end_time DATETIME NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Bids table
CREATE TABLE bids (
    id INT PRIMARY KEY AUTO_INCREMENT,
    amount DECIMAL(10,2) NOT NULL,
    bid_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    auction_id INT,
    user_id INT,
    FOREIGN KEY (auction_id) REFERENCES auctions(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Notifications table
CREATE TABLE notifications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    message TEXT NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### 3. Configure Database Connection

Update `src/main/java/com/auction/util/DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/auction_db";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

## 🚀 Installation & Running

### Prerequisites

- Java 17 or higher
- Apache Maven 3.6+
- Apache Tomcat 10+
- MySQL 8.0+

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/online-auction-system.git
   cd online-auction-system
   ```

2. **Setup the database** (see Database Setup section above)

3. **Build the project**
   ```bash
   mvn clean package
   ```

4. **Deploy to Tomcat**
   - Copy `target/OnlineAuctionSystem-0.0.1-SNAPSHOT.war` to Tomcat's `webapps` folder
   - Or deploy via Tomcat Manager

5. **Access the application**
   ```
   http://localhost:8080/OnlineAuctionSystem/first.jsp
   ```

## 📱 Application Pages

| Page | URL | Description |
|------|-----|-------------|
| Landing | `/first.jsp` | Welcome page with login/register options |
| Login | `/login.jsp` | User authentication |
| Register | `/register.jsp` | New user registration |
| Home | `/index.jsp` | Browse all active auctions |
| Dashboard | `/dashboard` | User's auctions, bids, notifications |
| Create Auction | `/createAuction.jsp` | List a new item |
| Auction Details | `/auctionDetails.jsp?auctionId=X` | View and bid on auction |
| Profile | `/profile.jsp` | Update user information |
| Search | `/search.jsp` | Search for auctions |

## 🎨 Design Features

The application features a modern **dark theme** with:

- **Glassmorphism** - Translucent cards with blur effects
- **Gradient Accents** - Orange and cyan color scheme
- **Smooth Animations** - Hover effects and transitions
- **Responsive Layout** - Works on desktop and mobile
- **CSS Variables** - Consistent theming across all pages

## 🔧 Configuration

### Environment Variables (Optional)

```properties
DB_URL=jdbc:mysql://localhost:3306/auction_db
DB_USER=root
DB_PASSWORD=yourpassword
```

## 📝 API Endpoints (Servlets)

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/login` | POST | Authenticate user |
| `/register` | POST | Create new user |
| `/logout` | GET | End user session |
| `/createAuction` | POST | Create new auction |
| `/placeBid` | POST | Place bid on auction |
| `/updateProfile` | POST | Update user profile |
| `/search` | GET | Search auctions |
| `/dashboard` | GET | Get user dashboard data |

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## 👤 Author

**Ravi Nayak**

- GitHub: [@Ravi7141](https://github.com/Ravi7141)

