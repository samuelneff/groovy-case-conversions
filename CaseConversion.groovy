// https://onecompiler.com/groovy/3y69yqq62

def splitCaseParts(text) {
  def normalized = text
    .trim()
    .replaceAll(/([^A-Z])([A-Z])/) { _, before, after -> "$before $after" }
  return normalized.tokenize(/[-_\s ]+/)
}

def camelCase(text) {
  return splitCaseParts(text)
    .withIndex()
    .collect { 
      word, index -> 
        word.replaceAll(/^(.)(.+)/) { 
          full, firstChar, rest -> 
            (index == 0 ? firstChar.toLowerCase() : firstChar.toUpperCase() ) +
            rest.toLowerCase()
        } 
    }
    .join('')
}

def pascalCase(text) {
  return splitCaseParts(text)
    .collect { 
      word -> 
        word.replaceAll(/^(.)(.+)/) { 
          full, firstChar, rest -> 
            firstChar.toUpperCase() + rest.toLowerCase()
        } 
    }
    .join('')
}

def kebabCase(text) {
  return splitCaseParts(text)
    .join('-')
    .toLowerCase()
}

def screamingKebabCase(text) {
  return splitCaseParts(text)
    .join('-')
    .toUpperCase()
}

def snakeCase(text) {
  return splitCaseParts(text)
    .join('_')
    .toLowerCase()
}

def screamingSnakeCase(text) {
  return splitCaseParts(text)
    .join('_')
    .toUpperCase()
}

def test(
  inputs, 
  expectedCamelCase, 
  expectedPascalCase,
  expectedKebabCase,
  expectedScreamingKebabCase,
  expectedSnakeCase,
  expectedScreamingSnakeCase) {
  
  inputs.each {
    input ->
      def actualCameLCase = camelCase(input)
      def actualPascalCase = pascalCase(input)
      def actualKebabCase = kebabCase(input)
      def actualScreamingKebabCase = screamingKebabCase(input)
      def actualSnakeCase = snakeCase(input)
      def actualScreamingSnakeCase = screamingSnakeCase(input)

      def passed = actualCameLCase == expectedCamelCase &&
                  actualPascalCase == expectedPascalCase &&
                  actualKebabCase == expectedKebabCase &&
                  actualScreamingKebabCase == expectedScreamingKebabCase &&
                  actualSnakeCase == expectedSnakeCase &&
                  actualScreamingSnakeCase == expectedScreamingSnakeCase
      
      println(passed ? 'pass' : 'FAIL *** FAIL *** FAIL *** FAIL')
      println("      Input: $input")
      println()
      println("      Camel Case Expected      : $expectedCamelCase")
      println("      Camel Case Actual        : $actualCameLCase")
      println()
      println("      Pascal Case Expected     : $expectedPascalCase")
      println("      Pascal Case Actual       : $actualPascalCase")
      println()
      println("      Kebab Case Expected      : $expectedKebabCase")
      println("      Kebab Case Actual        : $actualKebabCase")
      println()
      println("      Screaming Kebab Expected : $expectedScreamingKebabCase")
      println("      Screaming Kebab Actual   : $actualScreamingKebabCase")
      println()
      println("      Snake Case Expected      : $expectedSnakeCase")
      println("      Snake Case Actual        : $actualSnakeCase")
      println()
      println("      Screaming Snake Expected : $expectedScreamingSnakeCase")
      println("      Screaming Snake Actual   : $actualScreamingSnakeCase")
      println()
  }
}

test(
  [
    "one two three",
    "oneTwoThree",
    "OneTwoThree",
    "one-two-three",
    "one_two_three",
    "ONE_TWO_THREE",
    "ONE TWO THREE",
  ],  
  "oneTwoThree",
  "OneTwoThree",
  "one-two-three",
  "ONE-TWO-THREE",
  "one_two_three",
  "ONE_TWO_THREE"
)
