# MarketPlace
## Proposal
This application will provide service similar to that of *Amazon*, *Flipkart*, or *Snapdeal*. It will have different
products that buyers can view and buy. Sellers can sell whatever (legal things) they like. Sellers get to choose the
price of the product and the shipping costs. Buyers get to choose from many options.

This application can be used by buyers and sellers to sell products. Instead of going to a shop, and searching through
all the aisles, one can simply search and get what they want within minutes. Sellers can sell their product to anyone
in the world, and their services won't be limited to a neighbourhood.

I chose this project because most e-commerce applications have different apps for buying and selling (*Amazon*, for
example, has an *Amazon shopping* app and *Amazon Seller Central* for buying and selling). I want to create an app 
which does both buying and selling in a single app.

## Citations
- *MarketPlaceApp* was inspired by *TellerApp* (https://github.students.cs.ubc.ca/CPSC210/TellerApp)
   and *JsonSerializationDemo*(https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
- The GUI was inspired by 
  https://stackoverflow.com/questions/21375255/jpanel-positions-and-sizes-changes-according-to-screensize/21376596#21376596
- The splash screen was inspired by
  https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui
- The event logging was inspired by
  https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

## User Stories
**For sellers:**
- As a user, I want to be able to add my products to marketPlace and specify 
the price of the product
- As a user, I want to be able to view the list of my products, along with their ratings,
their price, and number of buys
- As a user, I want to be able to remove a listing

**For buyers:**
- As a user, I want to be able to view the list of all the products available
- As a user, I want to be able to buy products
- As a user, I want to be able to view the list of products bought
- As a user, I want to be able to rate the products

**For both buyers and sellers:**
- As a user, I want to be able to save all the information (name, price, rating) to 
 the marketplace to file (if I so choose)
- As a user, I want to be able to load the marketplace from file (If I so choose)

## How to use the application?

- You can add product to the marketplace by clicking the button labelled `List a product`
- You can remove a product by clicking the button labelled `remove a listing`
- You can buy a product by clicking the button labelled `Buy`
- You can view your previous orders by clicking the button labelled `Previous orders`
- You can locate my visual component when you start up the application for the first time
- You can save the state of my application by clicking the `save` button at the bottom
- You can reload the state of my application by pressing the `load` button at the button

## Events

- When a product is added to the marketplace, the event `{productName} was added to the marketplace`
  is logged
- When a product is bought, the event `{productName} was bought` is logged
- When a product is removed, the event `{productName} was removed from marketplace` is logged
- When a product is rated, the event `{productName} was rated` is logged
- When products are added to the marketplace, or when seller views the listed products, 
  the event `displaying the products listed by the seller` is logged
- When the buyer views the bought products (for example, to view the past orders, or to rate 
  the product), the event `showing buyer products` is logged

## Future plan

If I had more time, I would improve the cohesion in my `MarketPlaceGUI` class. Right now, all of my buttons are defined
in this same class. I would like to create an abstract class called `Button`, and all of my buttons, like `Buy` button, 
`Sell` button, `Rate` button would extend this class. And each of the buttons will be defined in their own classes, 
for example, their should be class named `Rate`, `Buy`, `Sell` etc. Then, all saving functionality will be defined
in the `Save` button class, and all the loading functionality would be defined in the `Load` button class

Then, I would improve the cohesion in the `MarketPlaceApp` class. Instead of defining all the functionality in this
single class, I would create classes, `BuyerUi`, `SellerUi`, and `SavingUi`. `BuyerUi` will have all the UI related to 
the Buyer functionality, `SellerUi` will have all the UI related to the Seller, and the `SavingUi` will have all the 
console UI related to the saving and the loading functionality

I am using a random number generator to generate unique product IDs, but instead, I would use the Singleton design
pattern to provide a unique ID to all the products

Right now, the user needs to type in the productID to buy, rate or remove a product. If I had time, I would make 
the text clickable, so that user can just click a product to buy/rate/remove a product
