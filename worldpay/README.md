Offer Service
===

Scenario
---
Per Wikipedia, "an *offer* is a proposal to sell a specific product or service under specific conditions". As a *merchant* I offer goods for sale. I want to *create an offer* so that I can share it with my customers.

All my offers have shopper friendly *descriptions*. I *price* all my offers up front in a defined *currency*.

**An offer is time-bounded,** with the length of time an offer is valid for defined as part of the offer, and should **expire automatically**. Offers may also be explicitly *cancelled before they expire*.


Assumptions
---
Merchant and Customer id’s will not be kept on the Offer table as it is better to store that data in a normalised table instead [e.g. MerchantOffer]. 
All Data returned will be active [** NOT deleted or expired**].  Data produced by this REST service will be in **Json** format only. The API should validate against **Bad_Request / Offer Not_Found / Bad_Request invalidArgument** so that such occurrences can be handled.
No validation for Merchants deleting other Merchant Offers is made..  Cancelling an offer is the same as deleting it.


Solution
---
Merchant and Customer class will have a simple implementation.  As no specification is given for these classes it will be assumed that all Entity models will have an [Id], therefore a simple representation of Merchant and Customer is provided with 1 field in each of type [Id]. Create an Error class to aid with custom error handling.

```
[url's]
1. /offers - get all offers that have NOT expired.
2. /offers/{offer_id} - get offer that have NOT expired by its [Id]
3. /offers/create - create a new offer. example {"id": 3, "name": "name3","description": "description3","currencyCode": "currencyCode3","price": 12.99, "expire": "2019-05-27T22:26:02.569+0000", "active": true}
4. /offers/delete/{offer_id}  - delete a single offer.
5. /offers/cancel/{offer_id}  - cancel an offer that has NOT already expired.
```

H2 in memory database will have 4 records. Record 3 has a date in the past so should automatically be expired when an attempt to view it is made.  Record 3 will report a customer error of **OfferHasExpired** when attempting to view it.  All CRUD operations can be accessed from the url's listed above. 

Issues/Bugs
---
-  Unknown maven error reported when using spring-boot-starter-parent version 2.1.5.RELEASE.
**fix** [*use spring-boot-starter-parent version 2.1.4.RELEASE*] instead.

TechStack
---
Java 8, Spring Boot 2.1.4, Hibernate, H2, JPA, Juint4, SQL

