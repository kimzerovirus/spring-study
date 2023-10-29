package me.kzv.batch.example.reader.file;

import me.kzv.batch.entity.pay.Pay;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PayFieldSetMapper implements FieldSetMapper<Pay> {

    @Override
    public Pay mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Pay(fieldSet.readLong("amount"),
                fieldSet.readString("txName"),
                fieldSet.readString("txDateTime"));
    }
}
