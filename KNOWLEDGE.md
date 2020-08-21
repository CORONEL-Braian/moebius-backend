## Architecture

_feature/data/datasource_: contains the data sources (i.e. dbms) of the resources, that is, the interfaces (stores) and the resource implementations.
    Interface repository-source maintains the principle of investment of dependencies between our
    repository and the warehouse of the deceased, which eliminates responsibilities.
    
_data-core_: Data base managment system and mappers for features 