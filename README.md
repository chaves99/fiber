# fiber

Fiber is an app for manage and store your diet.

### Class Structure:

###### User:
* id
* name
* email
* list of DietSeason ids

###### DietSeason:
* id
* name
* description
* caloriesGoal
* carbohydrateGoal
* proteinGoal
* fatGoal
* initialDate
* finalDate
* active
* list of Meal ids

###### Food:
* id
* name
* baseQuantity
* carbohydrate
* protein
* fat
* calories

###### Meal:
* id
* description
* day
* hour
* order
* list of food
* season id

###### Food per Meal:
* food id
* meal id
* quantity

