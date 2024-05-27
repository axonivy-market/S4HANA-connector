# S4HANA

This connector shows an easy way to connect to the SAP S4HANA database. 

The focus here is on the business partner domain. In the example, a connection to the database is established and all business partners are listed together with their email addresses.
Please note: In the example, a connection to [SAP Demo API](https://api.sap.com/api/BusinessPartner_APIs/tryout) is established, i.e. it is necessary that you first create an account here.


## Demo

Demo tries to connect to SAP and gets list of business partners

## Setup

Please set the global variables to the SAP needs. Maybe you need to add a certificate to connect to SAP via SSL.

```
@variables.yaml@
```

