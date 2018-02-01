(defun solution (banks)
	(setq seen (cons banks '()))
	(setq part1-banks '())	
	(setq newbanks banks)
	(setq part1-counter 0)
	(setq part2-counter 0)
	(loop
		(setq newbanks (realloc newbanks))
		(if (null part1-banks) (setq part1-counter (1+ part1-counter)) (setq part2-counter (1+ part2-counter)))
		(cond 
			((not (null part1-banks))  (if (equal newbanks part1-banks) (return (list part1-counter part2-counter))))
			((member newbanks seen :test 'equal) (setq part1-banks newbanks))
			(t (push newbanks seen))
		)
	)
)

(defun realloc (banks)
	(setq m (reduce #'max banks))
	(setq pos (position m banks))
	(realloc_r (replace-nth banks pos 0) m (next-pos pos (list-length banks)))
)


(defun realloc_r (banks m pos)
	(if (= 0 m) 
		banks 
		(realloc_r (incr-nth banks pos) (1- m) (next-pos pos (list-length banks)))
	)
)

(defun replace-nth (l n replacement)
	(cond
 		((null l) '())
		((eq n 0) (cons replacement (cdr l)))
		((cons (car l) (replace-nth (cdr l) (1- n) replacement)))
	)
)

(defun incr-nth (l n) (replace-nth l n (1+ (nth n l))))

(defun next-pos (n max) (mod (1+ n) max))

(setq result (solution '(0 2 7 0)))
(format t "~a expected: (5 4), actual:~a~%" (if (equal '(5 4) result) "OK" "FAIL") result)
(format t "~a~%" (solution '(4 1 15 12 0 9 9 5 5 8 7 3 14 5 12 3)))
