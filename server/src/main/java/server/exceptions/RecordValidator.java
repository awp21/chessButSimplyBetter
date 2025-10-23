package server.exceptions;

import java.lang.reflect.RecordComponent;

public class RecordValidator {

    /**
     * Checks all fields of the given record for null values.
     * Throws an IllegalArgumentException if any field is null.
     *
     * @param record the record instance to validate
     * @throws IllegalArgumentException if any record component is null
     */
    public static void validateNonNullRecord(Object record) throws BadRequestException{
        if (record == null) {
            throw new BadRequestException("Error: Record cannot be null.");
        }

        Class<?> clazz = record.getClass();

        if (!clazz.isRecord()) {
            throw new BadRequestException("Error: Provided object is not a record: " + clazz.getName());
        }

        for (RecordComponent component : clazz.getRecordComponents()) {
            try {
                Object value = component.getAccessor().invoke(record);
                if (value == null) {
                    throw new BadRequestException(
                            "Error: Field '%s' in record '%s' is null.".formatted(component.getName(), clazz.getSimpleName())
                    );
                }
            } catch (ReflectiveOperationException e) {
                throw new BadRequestException("Error: Failed to access field: " + component.getName());
            }
        }
    }
}