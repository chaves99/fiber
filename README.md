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
* list of MealDay ids

###### Food:
* id
* name
* baseQuantity
* carbohydrate
* protein
* fat
* calories
* list of meal

###### Meal:
* id
* description
* list of food
* list of mealDay

###### MealDay:
* id
* date
* list of meal


