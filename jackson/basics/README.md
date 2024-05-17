# Jackson basics

## Serialization annotations
- Called `@Json*Getter`, e.g. `@JsonGetter`
- It's getter because we're getting the fields from POJO in order to create serialized representation

### JsonPropertyOrder
- Defines the serialization order

### JsonAnyGetter
- Used to get key/value pairs for serialization, e.g. to serialize `Map<K, V>`

### JsonGetter
- Defines method for serializing of the field
- Property `value` depicts the name of the field in the serialized representation
