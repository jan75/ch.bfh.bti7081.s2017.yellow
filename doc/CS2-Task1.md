# CS2 Task 1: Elaborate Use Cases
## 1.1 Potential users
 - Patient
    * Measure current blood sugar level
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
	
## 1.2 Use Case Diagram
![Use Case Diagram](media/CS2/Task1/CS2_Task1_UseCaseDiagram.png)

## 1.3 Use cases
### Case 1
#### General Informations
|       Title       |   Description | 
| -------------     | ------------  |
| Name & Nr         | Case 1        |
| Scenario          | Load battery  |
| Short description | The battery is nearly empty and the patient has to reload the PIP | 
| Actors            | Patient, PIP  | 
| Start condition   | PIP alarms patient that battery is on low level   | 
| Result            | Battery is fully charged  |

#### Steps
| Nr.    | Actor         | Description  |
| ------ | ------------- | -----        |
| 1      | PIP           | Alert on display "low battery" |
| 2      | Patient       | Connect PIP to power with cable |
| 3      | PIP           | Display shows that PIP is charging now |
| 4      | PIP           | Alert on display "battery fully charged" |


#### Exceptions, Variants
| Nr.   | Actor             | What         |
| ----- | -------------     | -----             |  
| 1.0   | PIP               | PIP not charging while connected to power |
| 1.1   | Patient           | Call technical staff for replacement |
| 2.0   | Patient           | Cannot find charging cable    |
| 2.1   | Patient           | Call technical staff for new cable    |


### Case 2
#### General Informations
|       Title       |   Description                                |
| ----------------- | -------------------------------------------- |
| Name & Nr         | Case 2                                       |
| Scenario          | Check blood sugar level                      |
| Short description | Patient checks his current blood sugar level | 
| Actors            | Patient, PIP                                 | 
| Start condition   | PIP is running and has enough battery        | 
| Result            | Sugar level showed                           |

#### Steps
| Nr.    | Actor         | Description                                  |
| ------ | ------------- | -------------------------------------------- |
| 1      | Patient       | Patient presses the button "Check level"           |
| 2      | PIP           | Measure current blood sugar level            |
| 3      | PIP           | Calculate value and compare with recent data |
| 4      | PIP           | PIP show level and give an advice            |
| 5      | Patient       | Patient can read message                     |


#### Exceptions, Variants
| Nr.   | Actor             | What                             |
| ----- | -------------     | -----                            |  
| 1.0   | Patient           | Patient can't press button       |
| 2.0   | PIP               | PIP can't measure current level, because sensor is defect --> show alarm message |
| 3.0   | PIP               | Measure history is not available (initial measure) |
| 3.1   | PIP               | No advice showed |
| 4.0   | PIP               | Blood sugar level is very low --> display alarm |

### Case 3 
#### General Informations
|       Title       |   Description | 
| -------------     | ------------  |
| Name & Nr         | Case 3        |
| Scenario          | Injection of insulin|
| Short description | The Patient activates the insulin injection function, the system calculates the dose and injects if needed | 
| Actors            | Patient, PIP  | 
| Start condition   | PIP is running and insulin is available | 
| Result            | injection and messages |

#### Steps
| Nr.    | Actor         | Description  |
| ------ | ------------- | -----        |
| 1      | Patient       | Patient presses button injection |
| 2      | PIP           | Calculate the dose based on the actual blood sugar level and the information of the previous step|
| 3      | PIP           | Injection of insulin using a pump |
| 4      | Patient       | Patient can read message|


#### Exceptions, Variants
| Nr.   | Actor             | What         |
| ----- | -------------     | -----        |  
| 1.0   | Patient           | Patient can't press button       |
| 2.0   | PIP               | PIP can't measure current level, because sensor is defect --> show alarm message |
| 2.1   | PIP               | Measure history is not available (initial measure) |
| 3.0   | PIP               | Pump is defect --> display alarm |
| 3.1   | PIP               | Insulin reservoir is empty --> display alarm |
