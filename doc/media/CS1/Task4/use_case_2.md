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
|2.0|LMHD|is informed about an emergency situation|
|2.1|LMHD|decides which persons have to be informed|
|2.2|LMHD|opens the web app|
|2.3|LMHD|navigates to the contact feature|
|2.4|LMHD|sees the most important contacts listed and calls them|
|2.5|LMHD|searches contacts by patient name or number|
|2.6|LMHD|sees contacts that are relevant for this patient|


### Exceptions / Variants
|Nr.|Who|What|
|-|-|-|
|2.4|LMHD|does not see an important contact in the list|
|2.4.1|LMHD|searches for the contact|
|2.4.2|LMHD|adds the contact to the list|
|2.5|LMHD|searches a specific contact|