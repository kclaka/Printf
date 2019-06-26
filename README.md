## Exercise: Printf (30 Points)

The project name of this exercise is **Printf** 

### Problem Description

The purpose of this assignment is to give you an opportunity to apply what you learned about methods as well as learn more about what the `printf` method does.

You are going to write a Main class that contains at least one method other than `main`. You can also define other methods that help to better organize the code and avoid code duplication.

The signature of the method (i.e. the input parameters, name and return type) of this method will be as follow:

```java 
static public String format(String format, Object... values)

```

The first thing you should notice is that `printf` is a bit of a misnomer, since the method is actually called `format`. There's a method to my madness. I want the method that you write to be testable by calling directly from the unit tests. In the past I've been capturing what you send to the screen. I promised the unit tests would be easier once you started using methods, and by having the method return a string rather than print to the console does just that. At the same time I want you to get a better understanding of how `printf` works, so I called the project  `printf` instead of format.

Your `format` method should work similarly to `printf` of `java.io.PrintStream` (for example, System.out) and `format` of `java.lang.String`. It takes to parameters. The first is the format string, a string with text that may or may not including formatting information. Formatting information is demarked by a '#' character. The second argument is a special type of argument that can contain 0 or more arguments, often referred to as a variable argument, or colloquially, vararg. Methods normally need to declare exactly how many arguments they contain. The vararg, denoted by '...', is the exception and offers a great deal of flexibility. This means that the `format` method can be called with as many argument as you need. Be careful, however, you need to have exactly one argument for each '%' character you have that is intended to be a formatted out put.

For more information about `printf` in general see [this Java tutorial](https://docs.oracle.com/javase/tutorial/java/data/numberformat.html) or if you on a Mac or Linux machine type the command `man printf` in a terminal.

Before you panic, I'm not requiring, for this assignment, that you implement all the formats specified in those documents. I'm only requiring three, string, integer and float, and the optional hex for extra credit. That means you need to be able to properly format '`%s`', '`%d`' and '`%f`' and optionally '`%x`'. You will need to also handle the width, for string, integer and float, as well as precision for float. What that means is that `'%10s'`, should take a least 10 characters and be right justified. For floating point that means that '`%10.2f`' must take up at least 10 spaces, including the '.', whole and fractional parts of the number. You don't need to do any error checking if the string contains a precision for integer or string, you can just ignore it.

If you decide to implement `%x`, hexadecimal formatting, you can earn upto 7 points extra credit. You will 5 points extra credit if you implement either upper or lower case formatting. Additionally, if you implement both upper and lower case formatting, you will earn 7 points. That means if you specify `format("%x", 0xabcd)` it would return `abcd`. If you specify `format("%X", 0xabcd) it would return `ABCD`.

Below is a synopsis of what your `format` method should handle.

| Format Specifier | Description                                                                    | Example |
|------------------|--------------------------------------------------------------------------------|---------| 
| s                | Replaced by a string argument. Width can be specified. | `format("Hello %s", "World!")`;|
| d                | Replaced by an integer. Width can be specified. | `format("Sum. %5d", 23 + 7);`|
| f                | Replaced by a floating point number. Width and precision can be specified. | `format("Avg. %6.2f", 23.0/7);`|
| x                | Replaced by an integer in Hex format. Width can be specified. **Extra Credit**                 | `format("0x%x", 0xCAFE);`|


Hints:

- Use the [`Pattern`](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html) and [`Matcher`](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Matcher.html) class to parse the format specifiers and split the format string.

- The regular expression to recognize a format specifier is `%(\\d+)?(\\.(\\d+))?([SsDdFfXx])`.

- The specifier is in group 4 of the regular expression. The width is group 1 and the precision is group 3.

- To better understand regular expression in Java read this [tutorial](https://docs.oracle.com/javase/tutorial/essential/regex/), and understand what a group is read this [one](https://docs.oracle.com/javase/tutorial/essential/regex/groups.html);

### Getting Started

Like our last exercise, we are going to do this exercise by writing the source code that solves the problem first in **Main.java**. Using the techniques shown on the web page titled [How to Start Every Project in this Class](https://github.com/sbcc-cs105-spring2016/HowToStartEveryProject) create a source file called **Main.java**. This is where your code will go. 

Starting this week we don't have any code to copy for the assignment. You get to do it all! Don't forget to provide proper Javadoc documentation

Now go through Main.java, add the proper headers as in past assighments and then change the [CHANGE THIS TO YOUR INFORMATION] text to the proper items. There are two items to be changed.

Once you've written your code run the code by single clicking on **Main.java** in the package explorer and selecting **Run->Run** from the menu or using the keyboard shortcut. Examine the output. Does it do what you want? If not, how can you modify the code to do what you want?

###Running the Unit Tests

Next you'll want to run these unit tests. Start by right-clicking on the `unittest.cs105` package and selecting **Run As -> JUnit Test**. 

To go back to the project view, select the **Package Explorer** tab.

For this assignment the unit tests will test the following:

1. A call to `format` with only the format string, and no other arguments. For example, `format("Hello World!")` should return a string that only has "`Hello World!`" in it.

2. A call to `format` with a format string, that contains no specifiers but one argument, or a format string that contains a specifier but an argument of the wrong type should throw an IllegalArgumentException. For example, `format("Hello %s", 32)` should throw an IllegalArgumentException. 

3. A call to `format` with a format string that specifies and integer and then a positive number should return the proper string. The same test with a negative number should include a '-' sign. For example, `format("%d", -32)` should return the string "`-32`".

4. A call to `format` with a format that has multiple specifiers should format each specifier properly. Also, it tests that a regular string at the beginning or the end also work as expected. For example, `format(" %d %s %.1f", 32, "32", 32.1)` should return "` 32 32 32.1`".

5. A call to `format` with a format string that contains a specifier for a float with no precision should assume a precision of zero and therefore return a string with an integer. For example, `format("%f", 32.1)` should return "`32`".

6. A call to `format` with a format specifier that contains a specifier for a `float` and an argument with Math.PI. This test is run in several variants testing widht, precision and sign. For example, `format("%10.5f", Math.PI")` should return "`   3.14159`".

7. A call to `format` with a format specifier that container a specifier for a hexadecimal `int`, and an argument with a number. This test is run twice to see if either or both of lower and upper case are supported. Failing this test does not mean you fail in general, just that you don't get some or all of the extra credit.

### How to turn in this exercise

The first step of turning in your code is to commit and push your code to GitHub. Once you've completed this step your code will be on GitHub in your repository, not the repository for the class. This will allow you to use all your projects later as a portfolio.

To start the process write click your project and select **Team -> Commit...** and follow the usual procedures.

####Completing the turn-in process

Now to complete the turn-in process, once you confirmed that your code is on GitHub, you need to create a **pull request** against the class GitHub repository. This action will indicate to the original project that you have finished your coding and it will create a place to give feedback on a line by line basis. 

Go to **your** repository for this assignment on GitHub and click on the **Pull Request** icon.
 
1\. Click on **New pull request**

2\. Click on **Create pull request**
