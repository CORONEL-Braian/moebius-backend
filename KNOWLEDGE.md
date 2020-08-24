## Architecture

_feature/data/datasource_: contains the data sources (i.e. dbms) of the resources, that is, the interfaces (stores) and the resource implementations.
    Interface repository-source maintains the principle of investment of dependencies between our
    repository and the warehouse of the deceased, which eliminates responsibilities.
    
_data-core_: Data base managment system and mappers for features 



## Deployment Environment



 * **Development server**: Acting as a sandbox where unit testing may be performed by the developer
 
        development.möbius.app

 * **Integration server**: CI build target, or for developer testing of side effects
  
        integration.möbius.app

 * **Testing server**: The environment where interface testing is performed. 
A quality control team ensures that the new code will not have any impact on 
the existing functionality and tests major functionalities of the system after
deploying the new code in the test environment.
    
        testing.möbius.app

 * **Staging server**: Mirror of production environment

        staging.möbius.app

 * **Production server**: Serves end-users/clients

        api.möbius.app
    
*master <- staging <- testing <- integration <- development*
    
    
Branching system:
----------------

*version_x/feature_y/issue_z*