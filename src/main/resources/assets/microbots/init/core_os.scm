(begin
    ; Movement
    (define move-west (macro (value) (move "west" value)))
    (define move-east (macro (value) (move "east" value)))
    (define move-north (macro (value) (move "north" value)))
    (define move-south (macro (value) (move "south" value)))
    ; Maths
    (define PI 3.14159265359)
    (define TAU (* 2 PI))
    (define square (macro (args) (* args args)))
    ; ====================================================
    ;
    ;                   MicroOS v0.0.1.0
    ;
    ; ====================================================
    (define main-loop
        (lambda (args)
            (display ">> ")
            (define input (poll "send"))
            (if (null? input)
                (begin
                    (newline)
                    (main-loop))
                (begin
                    (display input)
                    (newline)
                    (main-loop)))))
    (cls)
    (set-cursor-pos 1 1)
    (display "MicroOS v1.0.0.0") (newline)
    (main-loop))