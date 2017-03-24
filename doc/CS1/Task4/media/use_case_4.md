| Name                          | Estimate needed resources per patient                                                                                |
|-------------------------------|-----------------------------------------------------------------------------------------------------------| 
| Number                        | 4                                                                                                         | 
| Actors                        | Employee                                                      | 
| Short description             | The Employee knows the patient and can therefore estimate how much resources must be assigned to the patient.                                    | 
| Trigger                       | A new patient arrives or the condition of a patient changes.                                          | 
| Results                       | The patient condition is updated and available in the planning tool for the LMHD. |              
                                                                                                                                            
### Flow

|Nr.|Who|What|
|-|-|-|
|4.0|Employee|takes in a new patient|
|4.0|Employee|opens web app|
|4.0|Employee|navigates to the estimation feature|
|4.0|Employee|creates a new patient profile|
|4.0|Employee|enters patient condition and saves|


### Exceptions / Variants
|Nr.|Who|What|
|-|-|-|
|4.0|Employee|observes a change of the condition of a patient|
|4.0.1|Employee|opens app|
|4.0.2|Employee|navigates to the estimation feature|
|4.0.3|Employee|enters the name or number of the patient|
|4.0.4|Employee|selects the patient|
|4.0.5|Employee|changes the patient condition and saves|