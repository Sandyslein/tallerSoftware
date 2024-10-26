package Manager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPA {

    Map<String, String> EntitiesMap;

    public JPA(Map<String, String> entitiesMap) {
        EntitiesMap = entitiesMap;
    }

    private List<Map<String, String>> readCsv(String filePath) {
        List<Map<String, String>> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] headers = csvReader.readNext();  // Leer la primera fila como encabezados
            String[] values;

            while ((values = csvReader.readNext()) != null) {
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    record.put(headers[i], values[i]);
                }
                records.add(record);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }

    public <T> List<T> getAll(String entityName, Class<T> clazz) {
        List<T> entities = new ArrayList<>();
        List<Map<String, String>> records = this.readCsv(this.EntitiesMap.get(entityName));

        for (Map<String, String> record : records) {
            try {
                // Crear una nueva instancia de la clase pasada por parámetro
                T instance = clazz.getDeclaredConstructor().newInstance();

                // Mapear cada valor del registro a los campos de la instancia
                for (Map.Entry<String, String> entry : record.entrySet()) {
                    String fieldName = entry.getKey();
                    String fieldValue = entry.getValue();

                    // Obtener el campo de la clase
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);

                    // Convertir el valor de String al tipo adecuado y asignarlo al campo
                    Object value = convertValue(field.getType(), fieldValue);
                    field.set(instance, value);
                }

                entities.add(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entities;
    }

    private Object convertValue(Class<?> fieldType, String value) {
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(value);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(value);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            return value;  // Retorna el valor como String si no es un tipo numérico o booleano
        }
    }


}
