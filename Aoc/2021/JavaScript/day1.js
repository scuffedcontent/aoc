const fs = require("fs");

const lines = fs.readFileSync("input.txt", {encoding: "utf-8"}).split("\n").filter((x) => Boolean(x)).map((x) => parseInt(x));

let increased = 0

for (let i = 1; i < lines.length; i++) {
    const element = lines[i];
    const prev = lines[i-1];
    if (element > prev) {
        increased++;
    }
}

let part2_increased = 0
for (let i = 3; i < lines.length; i++) {
    const element = lines[i] + lines[i-1] + lines[i-2];
    const prev = lines[i-1] + lines[i-2] + lines[i-3];
    if (element > prev) {
        part2_increased++;
    }
}

console.log(increased);
console.log(part2_increased);