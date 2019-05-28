Offer Service
===

Scenario
---
Per Wikipedia, "an *offer* is a proposal to sell a specific product or service under specific conditions". As a *merchant* I offer goods for sale. I want to *create an offer* so that I can share it with my customers.

All my offers have shopper friendly *descriptions*. I *price* all my offers up front in a defined *currency*.

**An offer is time-bounded,** with the length of time an offer is valid for defined as part of the offer, and should **expire automatically**. Offers may also be explicitly *cancelled before they expire*.


Assumptions
---
Merchant and Customer relationship id’s will not be kept on the Offer table as it is better to store that data in a normalised table instead [e.g. MerchantOffer]. 
All Data returned will be active [** NOT deleted or expired**].  Data produced by this REST service will be in **Json** format only. The API should validate against **Bad_Request / Offer Not_Found / Bad_Request invalidArgument** so that such occurrences can be handled. Cancelling an offer is the same as deleting it.


Solution
---
As per brief, the service is accessed via *HTTP*.  The project will run locally on port 8080.  below is a list of url's to access the the functionality of the API.  Please append your local ip address e.g. [**http://192.168.0.2:8080/**worldpay/offers]  or [**http://localhost:8080/**worldpay/offers].  There is a SQL file named *offer.sql* database located in the resource directory.  When the application is run the SQL file will be used to populate the data base.  the data contains [1] expired record which will not be visible but will report in Json format that the Offer has expired. 

```
[url's]
1. /worldpay/offers - get all offers that have NOT expired.
2. /worldpay/offers/{offer_id} - get offer that have NOT expired by its [Id]
3. /worldpay/offers/create - create a new offer. example {"id": 3, "name": "name3","description": "description3","currencyCode": "currencyCode3","price": 12.99, "expire": "2019-05-27T22:26:02.569+0000", "active": true}
4. /worldpay/offers/delete/{offer_id}  - delete a single offer.
5. /worldpay/offers/cancel/{offer_id}  - cancel an offer that has NOT already expired.
```

H2 in memory database will have 4 records. Record 3 has a date in the past so should automatically be expired when an attempt to view it is made.  Record 3 will report a customer error of **OfferHasExpired** when attempting to view it.  All CRUD operations can be accessed from the url's listed above. 

Issues/Bugs
---
-  Unknown maven error reported when using spring-boot-starter-parent version 2.1.5.RELEASE.
**fix** [*use spring-boot-starter-parent version 2.1.4.RELEASE*] instead.

TechStack
---
Java 8, Spring Boot 2.1.4, Hibernate, H2, JPA, Juint4, SQL

