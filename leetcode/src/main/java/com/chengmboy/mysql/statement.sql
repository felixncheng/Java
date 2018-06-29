/*Combine Two Tables*/
SELECT
  FirstName,
  LastName,
  City,
  State
FROM Person
  LEFT JOIN Address
    ON Person.PersonId = Address.PersonId;

/*查询第二高的工资*/
SELECT DISTINCT Salary AS SecondHighstSalary
FROM Employee
ORDER BY Salary DESC
LIMIT 1 OFFSET 1;

/*
随着深入的学习sql，发现复杂的sql解决问题并不一定最优选择，
一般应用瓶颈存在于数据库，所以尽可能的让数据库的效率更高，
一些操作型动作可以交给应用层做，一，可以提高系统性能天花板，
二，易于扩展。
于是打算，这里将只补充经典sql。

*/