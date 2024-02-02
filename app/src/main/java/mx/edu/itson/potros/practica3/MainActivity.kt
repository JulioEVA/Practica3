package mx.edu.itson.potros.practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    // Global variables for the range of numbers and the generated number
    var minValue = 0
    var maxValue = 100
    var num = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // References to the UI elements
        val guessings: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        // Event listener for the "Generate" button
        generate.setOnClickListener {
            // Generate a random number within the range
            num = Random.nextInt(minValue, maxValue)
            // Display the generated number on the interface
            guessings.setText(num.toString())
            // Hide the "Generate" button and show the "Guessed" button
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        // Event listener for the "Up" button
        up.setOnClickListener {
            // Set the lower limit of the range to the current number
            minValue = num
            // Check if the limits are still valid
            if (checkingLimits()) {
                // Generate a new random number within the new range
                num = Random.nextInt(minValue, maxValue)
                // Display the new number on the interface
                guessings.setText(num.toString())
            } else {
                // Display an error message if the limits are not valid
                guessings.setText("No puede ser ;( Ganaste!")
            }
        }

        // Event listener for the "Down" button
        down.setOnClickListener {
            // Set the upper limit of the range to the current number
            maxValue = num
            // Check if the limits are still valid
            if (checkingLimits()) {
                // Generate a new random number within the new range
                num = Random.nextInt(minValue, maxValue)
                // Display the new number on the interface
                guessings.setText(num.toString())
            } else {
                // Display an error message if the limits are not valid
                guessings.setText(num.toString())
            }
        }

        // Event listener for the "Guessed" button
        guessed.setOnClickListener {
            if (!won) {
                // Display the guessed number if the user won
                guessings.setText("Adiviné tu número es: " + num)
                // Change the text of the "Guessed" button to "Play Again"
                guessed.setText("Jugar de nuevo")
                // Update the win state
                won = true
            } else {
                // Show the "Generate" button and hide the "Guessed" button
                generate.visibility = View.VISIBLE
                generate.setText("Toca generar para empezar")
                guessed.visibility = View.GONE
                // Reset the values and win state
                resetValues()
            }
        }
    }

    /**
     * This function resets the values of the variables used in the game.
     */
    fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    /**
     * This function checks if the range limits are valid.
     * @return true if the limits are valid, false otherwise.
     */
    fun checkingLimits(): Boolean {
        return minValue != maxValue
    }
}