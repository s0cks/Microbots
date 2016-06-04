(begin
    (define newline (macro (args) (display "\n")))
    (define run
        (lambda (args)
            (display "Hello World") (newline)
            (display "Test") (newline)
            (run)))
    (run))