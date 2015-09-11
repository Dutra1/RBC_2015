# RBC_2015

Para testear el commit, decidi modificar el readme.

-- Clonar el repo, el inicio --

git clone [codigo HTTP en la pagina]
cd RBC_2015

-- Obtener cambios --

git pull

-- Guardar cambios --

git status (para ver cambios)
git add . (para guardar los cambios en un commit)
git commit -m [nombre commit] (declara el commit)
git push (pushea los cambios al repo)

-- Moverse en ramas --

git checkout master (pararse en rama master)
git pull (asegurarse de estar al dia)
git checkout -b [nueva branch] (crea una nueva branch desde master)
git status (checkear que estoy en la nueva branch)
// Hacer cosas
// Guardar las cosas
git checkout master (volver a master)
git merge [nueva branch] (trae los cambios commiteados de nueva branch a master)
git push (pushea el nuevo master, con los cambios de la otra branch)