| Name                          | Write/Read wiki articles                                                                                  |
|-------------------------------|-----------------------------------------------------------------------------------------------------------| 
| Number                        | 1                                                                                                         | 
| Actors                        | Leader of Mental Health Department (LMHD), Employee                                                       | 
| Short description             | The LMHD writes informative articles that can be read by employees.                                       | 
| Trigger                       | An employee asks LMHD a question that was already asked before.                                           | 
| Results                       | The answer to the question is in the wiki. The employee can read the article instead of asking the LMHD.  |              
                                                                                                                                             
### Flow

|Nr.|Who|What|
|-|-|-|
|1.0|Employee|asks a question|
|1.1|LMHD|answers the question|
|1.2|LHMD|opens the web app|
|1.3|LHMD|navigates to the wiki feature|
|1.4|LHMD|creates a new entry, inputs the question|
|1.5|LHMD|inputs the answer and saves|
|1.6|Employee|does not know something|
|1.7|Employee|opens the web app|
|1.8|Employee|navigates to the wiki feature|
|1.9|Employee|inputs words into a search field|
|1.10|Employee|sees results|
|1.11|Employee|opens a result and reads the answer|

### Exceptions / Variants
|Nr.|Who|What|
|-|-|-|
|1.10|Employee|does not find a result for his question|
|1.10.1|Employee|creates a new question|
|1.10.2|LHMD|sees unanswered questions when he opens the web app|
|1.10.3|LHMD|opens the unanswered questions|
|1.10.4|LHMD|writes an answer to the question and saves|
|1.10.5|Employee|sees that his question has been answered|
|1.10.6|Employee|selects the question and reads the answer|