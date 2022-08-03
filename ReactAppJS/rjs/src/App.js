import React, { useState } from 'react'
import './/App.css'


function App() {
    const [count, setCount] = useState(0)
        
    function decrementCount() {
        setCount(prevCount => prevCount - 1)
    }

    function incrementCount() {
        setCount(prevCount => prevCount + 1)
    }
        return(
        <div className="button">
        <button className="button" onClick={decrementCount}>-</button>
        <span className="button">{count}</span>
        <button className="button" onClick={incrementCount}>+</button>
        </div>
    );
}

export default App;
