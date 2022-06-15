package de.jonasrotert.vcard.transformer.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contact
{

    @CsvBindByName(column = "First Name")
    private String firstName;

    @CsvBindByName(column = "Last Name")
    private String lastName;

    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "E-mail Address")
    private String eMailAddress;

    @CsvBindByName(column = "E-mail 2 Address")
    private String eMailAddress2;

    @CsvBindByName(column = "E-mail 3 Address")
    private String eMailAddress3;

    @CsvBindByName(column = "Home Phone")
    private String homePhone;

    @CsvBindByName(column = "Home Phone 2")
    private String homePhone2;

    @CsvBindByName(column = "Business Phone")
    private String businessPhone;

    @CsvBindByName(column = "Business Phone 2")
    private String businessPhone2;

    @CsvBindByName(column = "Mobile Phone")
    private String mobilePhone;

    @CsvBindByName(column = "Home Street")
    private String homeStreet;

    @CsvBindByName(column = "Home City")
    private String homeCity;

    @CsvBindByName(column = "Home State")
    private String homeState;

    @CsvBindByName(column = "Home Postal Code")
    private String homePostalCode;

    @CsvBindByName(column = "Home Country/Region")
    private String homeCountryRegion;

    @CsvBindByName(column = "Birthday")
    private String birthday;

    @CsvBindByName(column = "Gender")
    private String gender;
}
