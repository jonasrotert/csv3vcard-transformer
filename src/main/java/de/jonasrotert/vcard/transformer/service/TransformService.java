package de.jonasrotert.vcard.transformer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

import de.jonasrotert.vcard.transformer.dto.Contact;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.AddressType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.Birthday;
import ezvcard.property.Gender;
import ezvcard.property.Kind;
import ezvcard.property.Revision;
import ezvcard.property.StructuredName;
import ezvcard.property.Uid;

@Service
public class TransformService
{
    private final Logger logger = LoggerFactory.getLogger(TransformService.class);

    public void exportContact(final Contact contact)
    {
        final VCard vcard = new VCard();

        final StructuredName n = new StructuredName();

        if (!StringUtils.isBlank(contact.getLastName()))
        {
            n.setFamily(contact.getLastName());
        }

        if (!StringUtils.isBlank(contact.getFirstName()))
        {
            n.setGiven(contact.getFirstName());
        }

        if (!StringUtils.isBlank(contact.getTitle()))
        {
            n.getPrefixes().add(contact.getTitle());
        }

        vcard.setStructuredName(n);

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            vcard.setBirthday(new Birthday(format.parse(contact.getBirthday())));
        } catch (final ParseException e)
        {
            this.logger.debug("Unable to parse birthdate");
        }

        if (!StringUtils.isBlank(contact.getEMailAddress()))
        {
            vcard.addEmail(contact.getEMailAddress());
        }

        if (!StringUtils.isBlank(contact.getEMailAddress2()))
        {
            vcard.addEmail(contact.getEMailAddress2());
        }

        if (!StringUtils.isBlank(contact.getEMailAddress3()))
        {
            vcard.addEmail(contact.getEMailAddress3());
        }

        if (!StringUtils.isBlank(contact.getHomePhone()))
        {
            vcard.addTelephoneNumber(contact.getHomePhone(), TelephoneType.HOME);
        }

        if (!StringUtils.isBlank(contact.getHomePhone2()))
        {
            vcard.addTelephoneNumber(contact.getHomePhone2(), TelephoneType.HOME);
        }

        if (!StringUtils.isBlank(contact.getBusinessPhone()))
        {
            vcard.addTelephoneNumber(contact.getBusinessPhone(), TelephoneType.WORK);
        }

        if (!StringUtils.isBlank(contact.getBusinessPhone2()))
        {
            vcard.addTelephoneNumber(contact.getBusinessPhone2(), TelephoneType.WORK);
        }

        if (!StringUtils.isBlank(contact.getMobilePhone()))
        {
            vcard.addTelephoneNumber(contact.getMobilePhone(), TelephoneType.CELL);
        }

        if (!StringUtils.isBlank(contact.getHomeStreet()))
        {
            final Address adr = new Address();

            adr.setStreetAddress(contact.getHomeStreet());
            adr.setLocality(contact.getHomeCity());
            adr.setPostalCode(contact.getHomePostalCode());
            adr.setCountry(contact.getHomeCountryRegion());
            adr.getTypes().add(AddressType.HOME);
            vcard.addAddress(adr);
        }

        vcard.setKind(Kind.individual());

        if (!StringUtils.isBlank(contact.getGender()))
        {
            if ("M".equals(contact.getGender()))
            {
                vcard.setGender(Gender.male());
            } else if ("F".equals(contact.getGender()))
            {
                vcard.setGender(Gender.female());
            } else
            {
                vcard.setGender(Gender.unknown());
            }
        } else
        {
            vcard.setGender(Gender.unknown());
        }

        vcard.setUid(Uid.random());
        vcard.setRevision(Revision.now());

        try
        {
            final File theDir = new File("./export/");
            if (!theDir.exists())
            {
                theDir.mkdirs();
            }

            final File file = new File("./export/" + contact.getFirstName() + "-" + contact.getLastName() + ".vcf");
            this.logger.info("Writing " + file.getName() + "...");

            Ezvcard.write(vcard).version(VCardVersion.V3_0).go(file);
        } catch (final IOException e)
        {
            this.logger.error(e.getLocalizedMessage());
        }

    }


    public void readFile(final String fileName)
    {
        try
        {
            final List<Contact> beans = new CsvToBeanBuilder(new FileReader(fileName)).withType(Contact.class).build().parse();
            beans.forEach(this::exportContact);
        } catch (final FileNotFoundException | IllegalStateException e)
        {
            this.logger.error(e.getMessage());
        }
    }
}
