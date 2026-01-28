// MongoDB initialization script
// Creates databases and users for customer and notification services

// Switch to admin database
db = db.getSiblingDB('admin');

// Create customer database and user
db = db.getSiblingDB('customer_db');
db.createCollection('customers');
db.customers.createIndex({ "email": 1 }, { unique: true });
db.customers.createIndex({ "phone": 1 });

// Create notification database and user
db = db.getSiblingDB('notification_db');
db.createCollection('notifications');
db.notifications.createIndex({ "recipientId": 1 });
db.notifications.createIndex({ "status": 1 });
db.notifications.createIndex({ "createdAt": -1 });

print('MongoDB initialization completed');