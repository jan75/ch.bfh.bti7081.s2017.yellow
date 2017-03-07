# CS2 Task 1: Elaborate Use Cases
### 1.1 Potential users
 - Patient
    * Measure blood sugar level
    * Check measured values
    * Inject Insulin
    * Load PIP battery
    * Alarm doctor or hospital
    * Refill insulin
 - Medical Staff
    * Analyze all measurements
    * Reporting
    * Refill insulin
 - Technical Staff
    * Check health of PIP
        * Batterylife
        * Insulin pump
    * Update software
    * Repairing
	
### 1.2 Use Case Diagram
![Use Case Diagram](https://raw.githubusercontent.com/jan75/ch.bfh.bti7081.s2017.yellow/CS2_Task1_Diagram/doc/media/CS2_Task1_UseCaseDiagram.png)

### 1.3 Use cases
#### Case 1
##### General Informations
|       Title       |   Description | 
| -------------     | ------------  |
| Name & Nr         | Case 1        |
| Scenario          | Load battery  |
| Short description | The battery is nearly empty and the patient has to realod the PIP | 
| Actors            | Patient, PIP  | 
| Start condition   | PIP alarms patient that battery is on low level   | 
| Result            | Battery is fully charged  |

##### Steps
| Nr.      | Actor         | Description  |
| ------   | ------------- | -----        |
| 1.1      | PIP           | Alert on display "low battery" |
| 1.2      | Patient       | Connect PIP to power with cable |
| 1.3      | PIP           | Display shows that PIP is charging now |
| 1.4      | PIP           | Alert on display "battery fully charged" |


##### Exceptions, Variants
| Nr.   | Actor             | What         |
| ----- | -------------     | -----             |  
| 1.0   | PIP               | PIP not charging while connected to power |
| 1.1   | Patient           | Call technical staff for replacement |
| 2.0   | Patient           | Cannot find charging cable    |
| 2.1   | Patient           | Call technical staff for new cable    |


#### Case 2
##### General Informations
|       Title       |   Description | 
| -------------     | ------------  |
| Name & Nr         | Case 2        |
| Scenario          | Check sugar level  |
| Short description | Patient | 
| Actors            | Patient, PIP  | 
| Start condition   | Patient checks with PIP his sugar level   | 
| Result            | Sugar level showed ond display with   |

##### Steps
| Nr.    | Actor         | Description  |
| ------ | ------------- | -----        |
| 1.1    |               | |
| 1.2    |               | |
| 1.3    |               | |
| 1.4    |               | |


##### Exceptions, Variants
| Nr.   | Actor             | What         |
| ----- | -------------     | -----        |  
| 1.0   |                   |  |
| 1.1   |                   |  |
| 2.0   |                   |  |
| 2.1   |                   |  |