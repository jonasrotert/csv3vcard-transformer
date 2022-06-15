# CSV to vCard Transformer

This is a small tool which transforms a simple csv file into a serie of vcard files.

The csv file must contain the following columns:
* First Name
* Last Name
* Title
* E-mail Address
* E-mail 2 Address
* E-mail 3 Address
* Home Phone
* Home Phone 2
* Business Phone
* Business Phone 2
* Mobile Phone
* Home Street
* Home City
* Home State
* Home Postal Code
* Home Country/Region
* Birthday

The file must be seperated by real commas.
The birthday must be formatted in YYY-MM-DD.

## Prerequisites
For the installation, please have Maven and JDK 11 installed.


## Get started
Clone the repository and compile it with maven.

Then, execute the jar with the parameter -i as the input file.

Example:

```
java -jar target/transformer-0.0.1-SNAPSHOT.jar -i "contacts.csv"  
```

## Example

The csv file might look like:

```
First Name,Last Name,Title,E-mail Address,E-mail 2 Address,E-mail 3 Address,Home Phone,Home Phone 2,Business Phone,Business Phone 2,Mobile Phone,Home Street,Home City,Home State,Home Postal Code,Home Country/Region,Birthday
Max,Mustermann,Dr.,max.mustermann@private-email.de,max.mustermann@private2-email.de,max.mustermann@business-email.de,00 49 123456789,00 49 123456789,00 49 123456789,00 49 123456789,00 49 123456789,Hauptsraße 14,Berlin,Berlin,12345,Deutschland,1970-01-01
```