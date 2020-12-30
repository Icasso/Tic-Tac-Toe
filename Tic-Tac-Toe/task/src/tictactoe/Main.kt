package tictactoe

import java.util.*
import kotlin.math.absoluteValue

val scanner = Scanner(System.`in`)
var isXPlayer = true
var gameEnded = false
fun main() {
    PrintEmptyGrid()
    val line: String = "         "
    AppendGrid(line)
}

fun result(line: String) {
    //Impossible
    //check for x's row
    var isImpossible = false
    var isXwin = false
    var isYwin = false
    var l = 0
    while (l <= 6) {
        if (line[l] != ' ' && line[l + 1] != ' ' && line[l + 2] != ' ') {
            if (line[l] == line[l + 1] && line[l + 1] == line[l + 2]) {
                if (line[l] == 'X') {
                    isXwin = true
                } else {
                    isYwin = true
                }
            }
        }
        l += 3
    }
    if (isXwin && isYwin) {
        println("Impossible")
        isImpossible = true
    }
    isXwin = false
    isYwin = false
    var j = 0
    while (j <= 2) {
        if (line[j] != ' ' && line[j + 3] != ' ' && line[j + 6] != ' ') {
            if (line[j] == line[j + 3] && line[j + 3] == line[j + 6]) {
                if (line[j] == 'X') {
                    isXwin = true
                } else {
                    isYwin = true
                }
            }
        }
        j += 1
    }
    if (isXwin && isYwin) {
        println("Impossible")
        isImpossible = true
    }

    //Count x and y
    var xNum = 0
    var oNum = 0
    var abs = 0
    for (i in line.indices) {
        if (line[i] == 'X') {
            xNum++
        }
        if (line[i] == 'O') {
            oNum++
        }
    }
    abs = xNum - oNum
    abs = abs.absoluteValue
    if (abs >= 2) {
        println("Impossible")
        isImpossible = true
    }


    if (!isImpossible) {
        var isFinished = false
        var topCounter = 0
        var rightCounter = 0
        var bottomCounter = 0
        var leftCounter = 0
        //Game not finished condition
        if (line[0] == ' ') {
            topCounter++
            leftCounter++
        }
        if (line[1] == ' ') {
            topCounter++
        }
        if (line[2] == ' ') {
            topCounter++
            rightCounter++
        }
        if (line[3] == ' ') {
            leftCounter++
        }
        if (line[5] == ' ') {
            rightCounter++
        }
        if (line[6] == ' ') {
            bottomCounter++
            leftCounter++
        }
        if (line[7] == ' ') {
            bottomCounter++
        }
        if (line[8] == ' ') {
            rightCounter++
        }

        if (topCounter > 0 && rightCounter > 0 && bottomCounter > 0 && leftCounter > 0) {
            //println("Game not finished")
            isFinished = true
        }

        // win condition (horizontal)
        var i = 0
        var isWon = false
        while (i <= 6) {
            if (line[i] != ' ' && line[i + 1] != ' ' && line[i + 2] != ' ') {
                if (line[i] == line[i + 1] && line[i + 1] == line[i + 2]) {
                    println(line[i] + " wins")
                    isWon = true
                    gameEnded = true

                }
            }
            i += 3
        }
        //win condition (vertical)
        if (!isWon) {
            var j = 0
            while (j <= 2) {
                if (line[j] != ' ' && line[j + 3] != ' ' && line[j + 6] != ' ') {
                    if (line[j] == line[j + 3] && line[j + 3] == line[j + 6]) {
                        println(line[j] + " wins")
                        isWon = true
                        gameEnded = true
                    }
                }
                j += 1
            }
        }
        //win condition (diagonal)
        if (!isWon) {
            if (!(line[0] == ' ' && line[4] == ' ' && line[8] == ' ')) {
                if (line[0] == line[4] && line[4] == line[8]) {
                    println(line[0] + " wins")
                    isWon = true
                    gameEnded = true
                }
            }
            if (!(line[2] == ' ' && line[4] == ' ' && line[6] == ' ')) {
                if (line[2] == line[4] && line[4] == line[6]) {
                    println(line[2] + " wins")
                    isWon = true
                    gameEnded = true
                }
            }
        }
        var isDrew = false
        //Draw
        if (!isWon && !isFinished) {
            for (i in line.indices) {
                if (line[i] == ' ') {
                    isDrew = false
                    break //contains empty
                } else {
                    isDrew = true
                }

            }
            if (isDrew) {
                println("Draw")
                gameEnded = true
            }
        }
    }
}

fun PrintEmptyGrid(){
    println("---------")
    println("| " + " " + " " + " " + " " + " " + " |")
    println("| " + " " + " " + " " + " " + " " + " |")
    println("| " + " " + " " + " " + " " + " " + " |")
    println("---------")
}

fun PrintGrid(line: String) {
    println("---------")
    println("| " + line[0] + " " + line[1] + " " + line[2] + " |")
    println("| " + line[3] + " " + line[4] + " " + line[5] + " |")
    println("| " + line[6] + " " + line[7] + " " + line[8] + " |")
    println("---------")
}

fun AppendGrid(line: String) {
    var run = false
    var lines = line.toCharArray()
    val char = 'X'
    val char2 = 'O'
    //start loop

    print("Enter the coordinates: ")

    var isNumeric = false
    while (!isNumeric) {
        try {
            val firstCoordinates = scanner.nextInt()
            val secondCoordinates = scanner.nextInt()
            if ((firstCoordinates < 1 || firstCoordinates > 3) || (secondCoordinates < 1 || secondCoordinates > 3)) {
                println("Coordinates should be from 1 to 3!")
                AppendGrid(line)
            }
            if (firstCoordinates == 1 && secondCoordinates == 1) {
                if (lines[0] == char || lines[0] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[0] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[0] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 1 && secondCoordinates == 2) {
                if (lines[1] == char || lines[1] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[1] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[1] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 1 && secondCoordinates == 3) {
                if (lines[2] == char || lines[2] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[2] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[2] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 2 && secondCoordinates == 1) {
                if (lines[3] == char || lines[3] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[3] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[3] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 2 && secondCoordinates == 2) {
                if (lines[4] == char || lines[4] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[4] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[4] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 2 && secondCoordinates == 3) {
                if (lines[5] == char || lines[5] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[5] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[5] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 3 && secondCoordinates == 1) {
                if (lines[6] == char || lines[6] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[6] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[6] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 3 && secondCoordinates == 2) {
                if (lines[7] == char || lines[7] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[7] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[7] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else if (firstCoordinates == 3 && secondCoordinates == 3) {
                if (lines[8] == char || lines[8] == char2) {
                    println("This cell is occupied! Choose another one!")
                    AppendGrid(line)
                } else {
                    if(isXPlayer) {
                        isXPlayer = false
                        lines[8] = char
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    } else {
                        isXPlayer = true
                        lines[8] = char2
                        val line2 = String(lines)
                        PrintGrid(line2)
                        result(line2)
                        if(!gameEnded) {
                            AppendGrid(line2)
                        }
                    }
                }
            } else {

            }
            isNumeric = true
        } catch (e: Exception) {
            println("You should enter numbers!")
            print("Enter the coordinates: ")
            scanner.nextLine()
        }
    }
}