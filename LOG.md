### Tips and Tricks
```
logging.level.org.hibernate.SQL=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### DB creation 
- spring.jpa.generate-ddl=true
- spring.jpa.hibernate.ddl-auto=create 
    create: create db, table if not exist 
    update: tim kiem nhung thay doi dua theo cau truc hien co, ko delete column ne modify entity
    create-drop: create db,table each launch app 
    validate: warring any chance related db structure 