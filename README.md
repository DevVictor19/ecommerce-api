# API for an e-commerce platform

## Functional Requirements

- Ability for users to sign up and log in.
- Ability to add products to a cart.
- Ability to remove products from a cart.
- Ability to view and search for products.
- Ability for users to checkout and pay for products.
- Admin panel to add products, set the prices and manage inventory

## Non Functional Requirements

- Use spring security for manage authentication and authorization
- Use asaas as payments gateway
- Use Next.js for build the frontend
- Use server-client architecture
- Use mongodb as database
- Use Java with Spring Boot for backend
- Add unit and integrations tests with JUnit and Mockito

## Entities

- Users
- Products
- Carts
- Orders

## Features

### User

- login user (public)
- signup user (public)

### Product

- create product (admin)
- delete product (admin)
- update product (admin)
- list products (client)
- search products (client)

### Cart

- add products to cart (client)
- clear cart (client)
- remove product of cart (client)
- find current user cart (client)

### Order

- create order (client)
- cancel order (client)
- list current user orders (client)
- pay order (client)
- list orders (admin)
- search orders (admin)