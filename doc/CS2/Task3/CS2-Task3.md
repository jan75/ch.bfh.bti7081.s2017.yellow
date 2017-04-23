# CS2 Task 3: Elicitate Requirements
## 1. High-level user requirements.
* Blood sugar control
* Automaticly injection of insulin
* Information management (alarm and messages)
* Programmable
* Creating reports 
* High operational availability
* Maintainable 
## 2. High-level system requirements.
### 2.1 Functional Requirements

* Measure blood sugar level
* Alarm patient if sugar level is critical
* PIP has to work under any weather condition
* Inject insulin to maintain a safe blood sugar level
* Continuous measurement
* Decision must be based on previous levels and rate of change of sugar level

### 2.2 Non-Functional Requirements    
 
* Speed
	- Blood Measurement duration < 2.0s
* Robustness
	- Restart < 5.0s
	- The alarm volume must be over 90dB and hearable everywhere
	- PIP has to work between -20Grad and +40Grad Celsius and has to be water resistant (conform to IP67)
* Size
	- Application size < 10mb
* Reliability
	- Available 99.0%
	
### 2.3 Domain Requirements

* The blood sugar level mesearument must conform to norm ISO 15197
* Official approval from national health authorities



