package de.jonasrotert.vcard.transformer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.jonasrotert.vcard.transformer.service.TransformService;

@SpringBootApplication
public class CsvToVcardTransformerApplication implements CommandLineRunner
{

    public static void main(final String[] args)
    {
        SpringApplication.run(CsvToVcardTransformerApplication.class, args);
    }

    private final Logger     logger = LoggerFactory.getLogger(CsvToVcardTransformerApplication.class);

    @Autowired
    private TransformService transformService;

    @Override
    public void run(final String... args) throws Exception
    {
        String fileName = "";

        for (int i = 0; i < args.length; ++i)
        {
            if ("-i".equals(args[i]) && i < args.length - 1)
            {
                fileName = args[i + 1];
            }
        }

        if (!StringUtils.isBlank(fileName))
        {
            this.logger.info("--0");
            this.transformService.readFile(fileName);
        }
    }

}
