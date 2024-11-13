Following are the API EndPoints :

1.Add or Update Product Inventory

curl --location --request PUT 'http://localhost:8080/api/v1/product' \
--header 'Content-Type: application/json' \
--data '{
    "productId": 1,
    "name": "Product A",
    "inStockQuantity": 10
}'

2. OnBoard a new Buyer

curl --location --request PUT 'http://localhost:8080/api/v1/buyer' \
--header 'Content-Type: application/json' \
--data '{
    "buyerId": 1,
    "name": "John Doe"
}'

3. Place an order(Consume product by Buyer)

curl --location 'http://localhost:8080/api/v1/product/1/buyer/1/order' \
--header 'Content-Type: application/json' \
--data '5'

4. Get Stock Quantity for a Product

curl --location 'http://localhost:8080/api/v1/product/1/stockInHand'

5. Get List of All Buyers and Products Ordered by Them

curl --location 'http://localhost:8080/api/v1/product/order'







