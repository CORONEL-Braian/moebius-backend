#### Error:

Unexpected exception thrown: com.fasterxml.jackson.databind.JsonMappingException: No suitable constructor found for type [simple type, class app.mobius.jsonApi.test.model.request.DataAtomicMock]

#### Solution:

Add a secondary constructor for type.

___

#### Error:  Jackson does not keep  CamelCase (for xProperty name)

When the map a class from KT to JSON, the properties with name xProperty are converted to xproperty 


#### Solution:

In property name, you should use anyProperty name or similar (not aProperty)
