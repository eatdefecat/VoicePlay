#--------------常用语法-------------#
#-keep {Modifier} {class_specification} 保护指定的类文件和类的成员
#-keepclassmembers {modifier} {class_specification} 保护指定类的成员，如果此类受到保护他们会保护的更好
#-keepclasseswithmembers {class_specification} 保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在。
#-keepnames {class_specification} 保护指定的类和类的成员的名称（如果他们不会压缩步骤中删除）
#-keepclassmembernames {class_specification} 保护指定的类的成员的名称（如果他们不会压缩步骤中删除）
#-keepclasseswithmembernames {class_specification} 保护指定的类和类的成员的名称，如果所有指定的类成员出席（在压缩步骤之后）
#-printseeds {filename} 列出类和类的成员-keep选项的清单，标准输出到给定的文件压缩
