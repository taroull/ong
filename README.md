# CSS
=================================
## API operations list

### Name
* `GET` **/v1/pharmacy?name=**[alphanumeric]
* `GET` **/v1/botiquin?name=**[alphanumeric]
* `GET` **/v1/disability?name=**[alphanumeric]
* `GET` **/v1/elderly?name=**[alphanumeric]
* `GET` **/v1/center?name=**[alphanumeric]

### Uri
* `GET` **/v1/pharmacyUri?uri=**[alphanumeric]
* `GET` **/v1/disabilityUri?uri=**[alphanumeric]
* `GET` **/v1/botiquinUri?uri=**[alphanumeric] 
* `GET` **/v1/elderlyUri?uri=**[alphanumeric]
* `GET` **/v1/centerUri?uri=**[alphanumeric]

### Around
* `GET` **/v1/pharmacyAround?uri=**[alphanumeric]**&radius=**[float]
* `GET` **/v1/centerAround?uri=**[alphanumeric]**&radius=**[float]
* `GET` **/v1/botiquinAround?uri=**[alphanumeric]**&radius=**[float]
* `GET` **/v1/disabilityAround?uri=**[alphanumeric]**&radius=**[float]
* `GET` **/v1/elderlyAround?uri=**[alphanumeric]**&radius=**[float]

### Category
* `GET` **/v1/centerCat?category=**[alphanumeric]

### DBpedia
* `GET` **/v1/info?uri=**[alphanumeric]

