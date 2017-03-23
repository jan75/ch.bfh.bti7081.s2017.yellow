| Name                          | Search contact information                                                                                 |
|-------------------------------|-----------------------------------------------------------------------------------------------------------| 
| Number                        | 2                                                                                                         | 
| Actors                        | Leader of Mental Health Department (LMHD)                                                      | 
| Short description             | The LMHD searches for contact information in the web app when other parties have to be informed about the situation.                                    | 
| Trigger                       | An emergency situation ocurrs.                                           | 
| Results                       | The LMHD has fastly gained access to the most important contacts needed for further actions. |              
                                                                                                                                            | 
### Flow

|Nr.|Who|What|
|-|-|-|
|1.0|LMHD|is informed about an emergency situation|
|1.1|LMHD|decides which persons have to be informed|
|1.2|LMHD|opens the web app|
|1.3|LMHD|navigates to the contact feature|
|1.4|LMHD|sees the most important contacts listed and calls them|
|1.5|LMHD|searches contacts by patient name or number|
|1.6|LMHD|sees contacts that are relevant for this patient|


### Exceptions / Variants
|Nr.|Who|What|
|-|-|-|
|1.4|LMHD|does not see an important contact in the list|
|1.4.1|LMHD|searches for the contact|
|1.4.2|LMHD|adds the contact to the list|
|1.5|LMHD|searches a specific contact|