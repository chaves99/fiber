# fiber

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
* list of MealDay ids

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
* list of food

###### MealDay:
* id
* date
* list of meal


