/*查询第二高的工资*/
SELECT Salary AS SecondHighstSalary
FROM Employee
ORDER BY Salary DESC
LIMIT 1 OFFSET 1;